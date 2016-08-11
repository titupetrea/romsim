/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radet.publisher;

/**
 *
 * @author Titu
 */
public class ThermalAgentStoppage {

    private final Address address;
    private final String startDate;
    private final String stopDate;
    private final String stoppageReason;
    private final String thermalAgent;

    public ThermalAgentStoppage(Address address, String thermalAgent, String startDate, String stopDate, String stoppageReason) {
        this.address = address;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.stoppageReason = stoppageReason;
        this.thermalAgent = thermalAgent;
    }

    public boolean hasChanged(ThermalAgentStoppage oldStoppage) {
        return getAddress().equals(oldStoppage.getAddress())
                && (getStartDate().equals(oldStoppage.getStartDate()) == false
                || getStopDate().equals(oldStoppage.getStopDate()) == false);
    }

    public Address getAddress() {
        return address;
    }

    public String getThermalAgent() {
        return thermalAgent;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public String getStoppageReason() {
        return stoppageReason;
    }

}
