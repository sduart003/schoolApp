/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MissingDataException;

public interface IPerson {
  
    public void setName(String p_name) throws MissingDataException;
    public void setAddress(String p_address) throws MissingDataException;
    public void setDateOfBirth(java.time.LocalDate p_dateOfBirth) throws MissingDataException;
   
    public String getName();
    public String getAddress();
    public java.time.LocalDate getDateOfBirth();
    
}
