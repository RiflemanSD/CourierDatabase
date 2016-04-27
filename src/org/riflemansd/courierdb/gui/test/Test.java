
package org.riflemansd.courierdb.gui.test;

import java.util.Date;
import org.riflemansd.courierdb.CourierDBM;
import org.riflemansd.courierdb.gui.searchpreview.GUIDataTest;
import org.riflemansd.courierdb.utils.MyUtils;

/**
 * <h1>CourierDB</h1>
 * <h3>Class Test</h3> 
 * <p>Created: 27 Απρ 2016, 10:39:36 μμ</p>
 *
 * <p>Copyright © 2016 | RiflemanSD | All right reserved</p>
 *
 * @author RiflemanSD
 */
public class Test {
    GUIDataTest gui;
    
    public Test() {
        gui = new GUIDataTest("VoucherID,Time", "s" , new Date());
        
        gui.setVisible(true);
        
        defineData();
    }
    
    public void defineData() {
        gui.clear();
        
        String[] data = CourierDBM.database.getPackagesIn();
        
        for (String d : data) {
            gui.addRow(CourierDBM.database.getVoucher(MyUtils.stringToInt(d.split(",")[0])).getName(), MyUtils.getDate(d.split(",")[1]));
        }
        
        gui.sort();
    }
}
