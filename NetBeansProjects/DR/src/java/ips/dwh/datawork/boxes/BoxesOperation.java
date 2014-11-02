package ips.dwh.datawork.boxes;

import ips.dwh.config.Configs;
import ips.dwh.datawork.DataWork;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class FileOperation
 */
public class BoxesOperation extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoxesOperation() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String webUserName = request.getRemoteUser();

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println("IS MULTIPART: " + isMultipart);


        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        System.out.println(getServletContext().getRealPath("/"));

        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);

        // Set factory constraints
        factory.setSizeThreshold(30);
        factory.setRepository(new File(Configs.getDocTmp()));

        System.out.println("TEMPORY REPOSITORY: " + factory.getRepository());


        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        //upload.setSizeMax(yourMaxRequestSize);

        String parentIdS = "root";

        try {
            // Parse the request
            List items = upload.parseRequest(request); /* FileItem */

            System.out.println("SIZE: " + items.size());

            Iterator iter = items.iterator();

            Map<String, String> formParams = new HashMap<String, String>();

            while (iter.hasNext()) { // Перебираем параметры
                FileItem item = (FileItem) iter.next();
                System.out.println("-----------------------------");

                if (item.isFormField()) { // Служит для чтения простых полей формы

                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");

                    System.out.println("NAME: " + name);
                    System.out.println("VALUE: " + value);

                    formParams.put(name, value);

                } else { // Этот блок для обоаботки файлов


                    String op = formParams.get("OPERATION");
                    parentIdS = formParams.get("PARENT_ID");

                    if (op.equals("add_box")) {
                        // Добавление коробки

                        // Получаем данные по вложению
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();

                        if (fileName == null || fileName.length() == 0) {
                            throw new Exception("Не указан фаил");
                        }

                        fileName = FilenameUtils.getName(fileName);

                        String contentType = item.getContentType();
                        boolean isInMemory = item.isInMemory();
                        long sizeInBytes = item.getSize();

                        System.out.println("FIELD NAME: " + fieldName);
                        System.out.println("FILE NAME: " + fileName);
                        System.out.println("CONTENT TYPE: " + contentType);
                        System.out.println("IS IN MEMORY: " + isInMemory);
                        System.out.println("SIZE IN BYTES: " + sizeInBytes);
                        String parentId = formParams.get("PARENT_ID");
                        System.out.println("Получен " + parentId);
                        //Создаем запись по вложению в базе данных

                        DataWork.ConnCont cc = new DataWork.ConnCont();
                        int boxId = DataWork.addBoxBegin(cc,
                                parentId,
                                formParams.get("KEY_NAME"),
                                formParams.get("DESCRIPTION"),
                                formParams.get("KEY_WORDS"),
                                formParams.get("KEY_FIOS"),
                                fileName,
                                formParams.get("ACCESS_LEVEL"),
                                formParams.get("K_DATE"));

                        //Производим запись вложения
                        String parDirPath = DataWork.getSectionPath(parentId);
                        parDirPath = parDirPath == null ? "" : parDirPath;


                        String repFilePath = parDirPath.equals("") ? boxId + "f" : parDirPath + "/" + boxId + "f";
                        String absFilePath = Configs.getDocRoot() + "/" + repFilePath;

                        System.out.println("Получен путь в репозитории: " + repFilePath);
                        System.out.println("Получен полный путь: " + absFilePath);

                        File uploadedFile = new File(absFilePath);
                        item.write(uploadedFile);

                        DataWork.addBoxEnd(cc, boxId + "", repFilePath);
                        cc.commitAndClose();

                    } else if (op.equals("edit_box")) {
                        // Редактирование коробки

                        String idS = formParams.get("ID");

                        {
                            // Работа с базой
                            DataWork.ConnCont cc = new DataWork.ConnCont();
                            DataWork.editBox(cc,
                                    idS,
                                    formParams.get("KEY_NAME"),
                                    formParams.get("DESCRIPTION"),
                                    formParams.get("KEY_WORDS"),
                                    formParams.get("KEY_FIOS"),
                                    formParams.get("ACCESS_LEVEL"),
                                    formParams.get("K_DATE"));

                            cc.commitAndClose();
                        }

                        // Получаем данные по вложению
                        {
                            String fieldName = item.getFieldName();
                            String fileName = item.getName();

                            if (fileName == null || fileName.length() == 0) {
                                continue;
                            }

                            fileName = FilenameUtils.getName(fileName);

                            DataWork.ConnCont cc = new DataWork.ConnCont();
                            DataWork.editBoxFileName(cc, idS, fileName);

                            DataWork.Result r = DataWork.getBox(webUserName,idS);
                            ResultSet rs = r.getRs();

                            if (rs.next()) {
                                String repFilePath = rs.getString("REP_FILE_PATH");

                                File uploadedFile = new File(Configs.getDocRoot() + "/" + repFilePath);
                                item.write(uploadedFile);
                            }

                            r.closeAll();

                            cc.commitAndClose();
                        }
                    }
                }
            }

            // Обработка удаления файла
            if (formParams.get("OPERATION").equals("del_box")) {

                parentIdS = formParams.get("PARENT_ID");

                // Удаление
                String idS = formParams.get("ID");

                DataWork.ConnCont cc = new DataWork.ConnCont();
                DataWork.delBox(cc, idS);

                // Получаем данные по коробке
                DataWork.Result r = DataWork.getBox(webUserName,idS);
                ResultSet rs = r.getRs();

                if (rs.next()) {
                    String repFilePath = rs.getString("REP_FILE_PATH");

                    File file = new File(Configs.getDocRoot() + "/" + repFilePath);
                    if(file.delete()){
                        cc.commitAndClose();
                    } else {
                        cc.rollbackAndClose();
                    }

                    
                }

                r.closeAll();
            }


            response.sendRedirect(response.encodeRedirectURL("../DataWork2.jsp?op=update&parent=" + parentIdS));


        } catch (Exception ex) {
            Logger.getLogger(BoxesOperation.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(1000, "Во время выполнения возникла ошибка:\n" + ex.getMessage());
        }
    }
}
