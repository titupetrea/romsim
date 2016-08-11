/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radet.publisher;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Titu
 */
public class ThermalAgentStoppages {

    private final Set<ThermalAgentStoppage> stoppages = new HashSet<>();

    public boolean add(ThermalAgentStoppage stoppage) {
        return stoppages.add(stoppage);
    }

    public Set<ThermalAgentStoppage> getStoppages() {
        return stoppages;
    }

}
