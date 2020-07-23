
package datamodels;

import exceptionhandlers.MissingDataException;
import java.time.LocalDate;
import interfaces.IPerson;

/**
 *
 * @author Steve
 */
public class Person implements IPerson {
    
    public String name;
    public String address;
    public LocalDate dateOfBirth;
   
    
    public void setName(String p_name) throws MissingDataException {
        // test for valid string
        if (p_name.length() == 0){
             throw new MissingDataException(":Name");
        }
        name = p_name;
    }
   
    public void setAddress(String p_address) throws MissingDataException{
        // test for valid address string
        if (p_address.length() == 0){
            throw new MissingDataException(":Address");
        }
        address = p_address;
    }
    
 
    public void setDateOfBirth(java.time.LocalDate p_dateOfBirth) throws MissingDataException {
        if (p_dateOfBirth == null){
            dateOfBirth = null;
            throw new MissingDataException ("Date of birth, not setting");
        } else{
        dateOfBirth = p_dateOfBirth;
        }
    }
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    
     public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
     
    public String toString(){
        return "name=" + name + ", address=" + address + ", dateOfBirth=" + 
                dateOfBirth;
    }

    
}
