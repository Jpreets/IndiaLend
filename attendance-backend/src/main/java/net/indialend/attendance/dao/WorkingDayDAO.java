/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.util.Date;
import net.indialend.attendance.service.*;
import java.util.List;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Holiday;
import net.indialend.attendance.bean.WorkingDays;

/**
 *
 * @author jaspreetsingh
 */
public interface WorkingDayDAO {

   public void persist(WorkingDays workingdays);
   
   public int truncate();
    
   public List<WorkingDays>  getAll();

}
