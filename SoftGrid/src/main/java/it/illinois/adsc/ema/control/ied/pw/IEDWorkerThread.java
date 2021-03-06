/* Copyright (C) 2016 Advanced Digital Science Centre

        * This file is part of Soft-Grid.
        * For more information visit https://www.illinois.adsc.com.sg/cybersage/
        *
        * Soft-Grid is free software: you can redistribute it and/or modify
        * it under the terms of the GNU General Public License as published by
        * the Free Software Foundation, either version 3 of the License, or
        * (at your option) any later version.
        *
        * Soft-Grid is distributed in the hope that it will be useful,
        * but WITHOUT ANY WARRANTY; without even the implied warranty of
        * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        * GNU General Public License for more details.
        *
        * You should have received a copy of the GNU General Public License
        * along with Soft-Grid.  If not, see <http://www.gnu.org/licenses/>.

        * @author Prageeth Mahendra Gunathilaka
*/
package it.illinois.adsc.ema.control.ied.pw;


import it.illinois.adsc.ema.control.conf.generator.ConfigGenerator;
import it.illinois.adsc.ema.control.ied.*;

import java.util.Map;

/**
 * Created by prageethmahendra on 11/2/2016.
 */
public class IEDWorkerThread implements Runnable, Comparable {

    private PWModelDetails pwModelDetails;
    private SoftGridIEDServer smartPowerIEDServer = null;

    public IEDWorkerThread(PWModelDetails s) {
        this.pwModelDetails = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. in Port = " + pwModelDetails.getPortNumber());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        smartPowerIEDServer = it.illinois.adsc.ema.control.ied.IEDServerFactory.getIedServer(pwModelDetails);
        if(smartPowerIEDServer == null)
        {
            System.out.println("Unable to create the IED Server instance.");
        }
        System.out.println("pwModelDetails.getModelNodeReference() = " + pwModelDetails.getModelNodeReference());
        try {
            smartPowerIEDServer.startServer(pwModelDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SoftGridIEDServer getSmartPowerIEDServer() {
        return smartPowerIEDServer;
    }

    @Override
    public String toString() {
        Map<String, String> keyValuePair = getKeyValuePairs();
        String iedType = keyValuePair == null ? "Virtual" : getIEDType();
        int portNumber = getPortNumber();
        String ioa = String.valueOf(portNumber - ConfigGenerator.FIRST_PORT);
        StringBuffer sb = new StringBuffer();
        sb.append(iedType).append(",").append(ioa).append(",");
        if (keyValuePair != null) {
            for (String key : keyValuePair.keySet()) {
                sb.append(key).append("=").append(keyValuePair.get(key)).append(" ");
            }
        }
        sb.append(",").append(String.valueOf(portNumber));
        return sb.toString();
    }

    public boolean isServerStarted() {
        return smartPowerIEDServer != null && smartPowerIEDServer.isServerStarted();
    }


    public String getIEDType() {
        return pwModelDetails.getDeviceName();
    }

    public int getPortNumber() {
        return pwModelDetails.getPortNumber();
    }

    public Map<String, String> getKeyValuePairs() {
        return pwModelDetails.getKeyValueFields();
    }

    public PWModelDetails getPwModelDetails() {
        return pwModelDetails;
    }

    public void setPwModelDetails(PWModelDetails pwModelDetails) {
        this.pwModelDetails = pwModelDetails;
    }

    @Override
    public int compareTo(Object o) {
        if (o != null && o instanceof IEDWorkerThread && pwModelDetails != null) {
            return this.pwModelDetails.compareTo(((IEDWorkerThread) o).getPwModelDetails());
        } else if (pwModelDetails == null && o instanceof IEDWorkerThread &&
                ((IEDWorkerThread) o).getPwModelDetails() == null) {
            return 0;
        } else {
            return -1;
        }
    }
}
