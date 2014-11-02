/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.model.objects.interfaces.impl;

import ips.prbase.dao.DAOImpl;
import ips.prbase.model.attributes.interfaces.IBasicAttribute;
import ips.prbase.model.attributes.interfaces.impl.DateAttribute;
import ips.prbase.model.attributes.interfaces.impl.ImageAttribute;
import ips.prbase.model.attributes.interfaces.impl.IntAttribute;
import ips.prbase.model.attributes.interfaces.impl.RefferenceAttribute;
import ips.prbase.model.attributes.interfaces.impl.TextAttribute;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kopychenko
 */
public class Person extends ObjectDescriptor {

    final String keyName = "PERSON";

    @Override
    public String getKeyName() {
        return this.keyName;
    }
    public static final String[] genders = {
        "Мужской",
        "Женский"
    };
    public static final String[] maritalStatuses = {
        "женат / замужем",
        "не женат / не замужем"
    };

    public String[] getGenders() {
        return genders;
    }

    public String[] getMaritalStatuses() {
        return maritalStatuses;
    }
    /**
     * Открытый блок
     */
    ImageAttribute foto = new ImageAttribute();
    TextAttribute fio = new TextAttribute();
    TextAttribute phones = new TextAttribute();
    TextAttribute post = new TextAttribute();
    DateAttribute birthday = new DateAttribute();
    IntAttribute hasChildren = new IntAttribute();
    TextAttribute birthPlace = new TextAttribute();
    TextAttribute abidingPlace = new TextAttribute();
    TextAttribute officialAutobiography = new TextAttribute();
    TextAttribute notesOnEducation = new TextAttribute();
    RefferenceAttribute education;
    {
        try {
            education = DAOImpl.getRefferenceAttribute("EDUCATION");
        } catch (Exception ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    TextAttribute NotesOnTheDegree = new TextAttribute();
    RefferenceAttribute degree;
    {
        try {
            degree = DAOImpl.getRefferenceAttribute("DEGREE");
        } catch (Exception ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    TextAttribute scopeOfProfessionalInterests = new TextAttribute();
    TextAttribute politicalViews = new TextAttribute();
    TextAttribute theDegreeOfPublicity = new TextAttribute();
    //Открытый блок
    IBasicAttribute[] openBlock = {foto, fio, phones, post, birthday,
        hasChildren, birthPlace, abidingPlace, officialAutobiography, education,
        notesOnEducation, degree, NotesOnTheDegree,scopeOfProfessionalInterests,
        politicalViews, theDegreeOfPublicity};
    /**
     * Закрытый блок
     */
    TextAttribute theTrueMotivation = new TextAttribute();
    TextAttribute personality = new TextAttribute();
    TextAttribute hobbies = new TextAttribute();
    TextAttribute thePresenceOfConflictPointss = new TextAttribute();
    TextAttribute theDegreeOfPersonalInfluence = new TextAttribute();
    TextAttribute thePresenceOfInfluentialPeople = new TextAttribute();
    TextAttribute ideologicalContradictions = new TextAttribute();
    TextAttribute prospectsForProfessionalAdvancement = new TextAttribute();
    TextAttribute theDailyContactsMeetings = new TextAttribute();
    TextAttribute availableSourcesOfInformation = new TextAttribute();
    TextAttribute contactsWithParties = new TextAttribute();
    TextAttribute mainSourcesOfFunding = new TextAttribute();
    TextAttribute ForeignSourcesOfFunding = new TextAttribute();
    TextAttribute additionalSourcesOfFunding = new TextAttribute();
    TextAttribute honors = new TextAttribute();
    TextAttribute gender = new TextAttribute();
    TextAttribute maritalStatus = new TextAttribute();
    // Закрытый блок
    IBasicAttribute[] hiddenBlock = {theTrueMotivation, personality, hobbies, 
        thePresenceOfConflictPointss, theDegreeOfPersonalInfluence,
        thePresenceOfInfluentialPeople, ideologicalContradictions,
        prospectsForProfessionalAdvancement, theDailyContactsMeetings,
        availableSourcesOfInformation, contactsWithParties, mainSourcesOfFunding,
        ForeignSourcesOfFunding, additionalSourcesOfFunding, honors, gender, maritalStatus};

    /**
     * Метод инициализации
     */
    @Override
    protected void init() {
        try {

            System.out.println("Инициализация персоны");
            //education = DAOImpl.getRefferenceAttribute("EDUCATION");
            //attributes.put(education.getKeyName(), education); // Потому что они еще не проинициализированы

            //degree = DAOImpl.getRefferenceAttribute("DEGREE");
            //attributes.put(degree.getKeyName(), degree);

            super.init();
            System.out.println("Инициализация персоны.");
            //((ITextAttribute) attributes.get("fio")).setText("Василий Пупкин");



        } catch (Exception ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Person() {
        init();
    }

    public Person(int descritoriD) {
        this.id = descritoriD;
        init();
    }

    public TextAttribute getFio() {
        return fio;
    }

    public void setFio(TextAttribute fio) {
        this.fio = fio;
    }

    public TextAttribute getPhones() {
        return phones;
    }

    public void setPhones(TextAttribute phones) {
        this.phones = phones;
    }

    public TextAttribute getPost() {
        return post;
    }

    public void setPost(TextAttribute post) {
        this.post = post;
    }

    public DateAttribute getBirthday() {
        return birthday;
    }

    public void setBirthday(DateAttribute birthday) {
        this.birthday = birthday;
    }

    public IntAttribute getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(IntAttribute hasChildren) {
        this.hasChildren = hasChildren;
    }

    public TextAttribute getOfficialAutobiography() {
        return officialAutobiography;
    }

    public void setOfficialAutobiography(TextAttribute officialAutobiography) {
        this.officialAutobiography = officialAutobiography;
    }

    public TextAttribute getAbidingPlace() {
        return abidingPlace;
    }

    public void setAbidingPlace(TextAttribute abidingPlace) {
        this.abidingPlace = abidingPlace;
    }

    public TextAttribute getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(TextAttribute birthPlace) {
        this.birthPlace = birthPlace;
    }

    public TextAttribute getNotesOnEducation() {
        return notesOnEducation;
    }

    public void setNotesOnEducation(TextAttribute notesOnEducation) {
        this.notesOnEducation = notesOnEducation;
    }

    public TextAttribute getNotesOnTheDegree() {
        return NotesOnTheDegree;
    }

    public void setNotesOnTheDegree(TextAttribute NotesOnTheDegree) {
        this.NotesOnTheDegree = NotesOnTheDegree;
    }

    public TextAttribute getScopeOfProfessionalInterests() {
        return scopeOfProfessionalInterests;
    }

    public void setScopeOfProfessionalInterests(TextAttribute scopeOfProfessionalInterests) {
        this.scopeOfProfessionalInterests = scopeOfProfessionalInterests;
    }

    public TextAttribute getPoliticalViews() {
        return politicalViews;
    }

    public void setPoliticalViews(TextAttribute politicalViews) {
        this.politicalViews = politicalViews;
    }

    public TextAttribute getTheDegreeOfPublicity() {
        return theDegreeOfPublicity;
    }

    public void setTheDegreeOfPublicity(TextAttribute theDegreeOfPublicity) {
        this.theDegreeOfPublicity = theDegreeOfPublicity;
    }

    public TextAttribute getForeignSourcesOfFunding() {
        return ForeignSourcesOfFunding;
    }

    public void setForeignSourcesOfFunding(TextAttribute ForeignSourcesOfFunding) {
        this.ForeignSourcesOfFunding = ForeignSourcesOfFunding;
    }

    public TextAttribute getAdditionalSourcesOfFunding() {
        return additionalSourcesOfFunding;
    }

    public void setAdditionalSourcesOfFunding(TextAttribute additionalSourcesOfFunding) {
        this.additionalSourcesOfFunding = additionalSourcesOfFunding;
    }

    public TextAttribute getAvailableSourcesOfInformation() {
        return availableSourcesOfInformation;
    }

    public void setAvailableSourcesOfInformation(TextAttribute availableSourcesOfInformation) {
        this.availableSourcesOfInformation = availableSourcesOfInformation;
    }

    public TextAttribute getContactsWithParties() {
        return contactsWithParties;
    }

    public void setContactsWithParties(TextAttribute contactsWithParties) {
        this.contactsWithParties = contactsWithParties;
    }

    public TextAttribute getHobbies() {
        return hobbies;
    }

    public void setHobbies(TextAttribute hobbies) {
        this.hobbies = hobbies;
    }

    public TextAttribute getHonors() {
        return honors;
    }

    public void setHonors(TextAttribute honors) {
        this.honors = honors;
    }

    public TextAttribute getIdeologicalContradictions() {
        return ideologicalContradictions;
    }

    public void setIdeologicalContradictions(TextAttribute ideologicalContradictions) {
        this.ideologicalContradictions = ideologicalContradictions;
    }

    public TextAttribute getMainSourcesOfFunding() {
        return mainSourcesOfFunding;
    }

    public void setMainSourcesOfFunding(TextAttribute mainSourcesOfFunding) {
        this.mainSourcesOfFunding = mainSourcesOfFunding;
    }

    public TextAttribute getPersonality() {
        return personality;
    }

    public void setPersonality(TextAttribute personality) {
        this.personality = personality;
    }

    public TextAttribute getProspectsForProfessionalAdvancement() {
        return prospectsForProfessionalAdvancement;
    }

    public void setProspectsForProfessionalAdvancement(TextAttribute prospectsForProfessionalAdvancement) {
        this.prospectsForProfessionalAdvancement = prospectsForProfessionalAdvancement;
    }

    public TextAttribute getTheDailyContactsMeetings() {
        return theDailyContactsMeetings;
    }

    public void setTheDailyContactsMeetings(TextAttribute theDailyContactsMeetings) {
        this.theDailyContactsMeetings = theDailyContactsMeetings;
    }

    public TextAttribute getTheDegreeOfPersonalInfluence() {
        return theDegreeOfPersonalInfluence;
    }

    public void setTheDegreeOfPersonalInfluence(TextAttribute theDegreeOfPersonalInfluence) {
        this.theDegreeOfPersonalInfluence = theDegreeOfPersonalInfluence;
    }

    public TextAttribute getThePresenceOfConflictPointss() {
        return thePresenceOfConflictPointss;
    }

    public void setThePresenceOfConflictPointss(TextAttribute thePresenceOfConflictPointss) {
        this.thePresenceOfConflictPointss = thePresenceOfConflictPointss;
    }

    public TextAttribute getThePresenceOfInfluentialPeople() {
        return thePresenceOfInfluentialPeople;
    }

    public void setThePresenceOfInfluentialPeople(TextAttribute thePresenceOfInfluentialPeople) {
        this.thePresenceOfInfluentialPeople = thePresenceOfInfluentialPeople;
    }

    public TextAttribute getTheTrueMotivation() {
        return theTrueMotivation;
    }

    public void setTheTrueMotivation(TextAttribute theTrueMotivation) {
        this.theTrueMotivation = theTrueMotivation;
    }

    public TextAttribute getGender() {
        return gender;
    }

    public void setGender(TextAttribute gender) {
        this.gender = gender;
    }

    public TextAttribute getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(TextAttribute maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public RefferenceAttribute getEducation() {
        return education;
    }

    public void setEducation(RefferenceAttribute education) {
        this.education = education;
    }

    public RefferenceAttribute getDegree() {
        return degree;
    }

    public void setDegree(RefferenceAttribute degree) {
        this.degree = degree;
    }

    public ImageAttribute getFoto() {
        return foto;
    }

    public void setFoto(ImageAttribute foto) {
        this.foto = foto;
    }

    public IBasicAttribute[] getHiddenBlock() {
        return hiddenBlock;
    }

    public IBasicAttribute[] getOpenBlock() {
        return openBlock;
    }

}
