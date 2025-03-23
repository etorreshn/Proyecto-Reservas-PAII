/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;


/**
 *
 * @author Edwin
 */
public interface BDOperations {
    public List<Object> getAll();
    Object getById(int id);
    boolean insert(Object object);
    boolean update(Object object);
    boolean delete(int id);

}
