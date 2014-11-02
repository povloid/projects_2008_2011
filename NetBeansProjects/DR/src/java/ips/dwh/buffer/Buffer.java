/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.dwh.buffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Буфер обмена
 * @author kopychenko
 */
public class Buffer {

    private List<Integer> selectedSections = new ArrayList<Integer>();
    private List<Integer> selectedBoxes = new ArrayList<Integer>();

    public Buffer() {
    }

    public List<Integer> getSelectedBoxes() {
        return selectedBoxes;
    }

    public void setSelectedBoxes(List<Integer> selectedBoxes) {
        this.selectedBoxes = selectedBoxes;
    }

    public List<Integer> getSelectedSections() {
        return selectedSections;
    }

    public void setSelectedSections(List<Integer> selectedSections) {
        this.selectedSections = selectedSections;
    }

}
