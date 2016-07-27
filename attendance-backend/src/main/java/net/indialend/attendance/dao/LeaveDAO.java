/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Leaves;
import net.indialend.attendance.bean.Staff;

/**
 *
 * @author jaspreetsingh
 */
public interface LeaveDAO {

    public void persist(Leaves leaves);

    public void update(Leaves leaves);
    
    public List<Leaves> getLeaves(long staffId, Date from , Date to);

}
