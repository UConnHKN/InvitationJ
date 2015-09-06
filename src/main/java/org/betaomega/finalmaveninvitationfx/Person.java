/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

/**
 *
 * @author Jordan Force
 */
public class Person implements Comparable<Object>{
    private String email;
    public Person(ColumnVariableMap columnMap, String[] row){
        this.setEmail(columnMap.getEmail(row));
    }
    public Person(String email){
        this.setEmail(email);
    }
    @Override
    public int compareTo(Object t) {
        if(!(t instanceof Person)){
            return 0;
        }else{
            Person p = (Person) t;
            return this.getEmail().compareTo(p.getEmail());
        }
    }
    
    @Override
    public boolean equals(Object t){
        if(!(t instanceof Person)){
            return false;
        }else{
            Person p = (Person) t;
            return this.getEmail().equals(p.getEmail());
        }
    }
    @Override
    public int hashCode(){
        return this.getEmail().hashCode();
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
