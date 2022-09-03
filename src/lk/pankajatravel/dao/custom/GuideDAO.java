/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import java.util.ArrayList;
import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Guide;

/**
 *
 * @author Ronila
 */
public interface GuideDAO extends CrudDAO<Guide, String>{
    
    public ArrayList<Guide> getSearchName(String name) throws Exception;
    
    public String getlastGuideID() throws Exception;
}
