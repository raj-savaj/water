package com.rj.watersupply.modal;

import java.util.ArrayList;

public class ResponseLogin {
    private float status;
    private String msg;
    ArrayList<Data> data = new ArrayList<Data>();

    public float getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


    public void setStatus( float status ) {
        this.status = status;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data
    {
        private int id;

        private String name;

        private String email;

        private String pass;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setEmail(String email){
            this.email = email;
        }
        public String getEmail(){
            return this.email;
        }
        public void setPass(String pass){
            this.pass = pass;
        }
        public String getPass(){
            return this.pass;
        }
    }
}


