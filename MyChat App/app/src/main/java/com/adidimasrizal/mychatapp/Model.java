package com.adidimasrizal.mychatapp;

import java.util.List;

public class Model {

    private List<Datachats> datachats;
    private Sendchat sendchat;
    private List<History> history;
    private List<Users> users;
    private Checkuser checkuser;

    public List<Datachats> getDatatchats() {
        return datachats;
    }

    public void setDatatchats(List<Datachats> datatchats) {
        this.datachats = datatchats;
    }

    public Sendchat getSendchat() {
        return sendchat;
    }

    public void setSendchat(Sendchat sendchat) {
        this.sendchat = sendchat;
    }

    public class Datachats{
        private String token_sender;
        private String name_sender;
        private String token_receiver;
        private String name_receiver;
        private String date;
        private String time;
        private String message;

        public String getToken_sender() {
            return token_sender;
        }

        public void setToken_sender(String token_sender) {
            this.token_sender = token_sender;
        }

        public String getName_sender() {
            return name_sender;
        }

        public void setName_sender(String name_sender) {
            this.name_sender = name_sender;
        }

        public String getToken_receiver() {
            return token_receiver;
        }

        public void setToken_receiver(String token_receiver) {
            this.token_receiver = token_receiver;
        }

        public String getName_receiver() {
            return name_receiver;
        }

        public void setName_receiver(String name_receiver) {
            this.name_receiver = name_receiver;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class Sendchat{
        private String token_sender;
        private String name_sender;
        private String token_receiver;
        private String name_receiver;
        private String message;

        public String getToken_sender() {
            return token_sender;
        }

        public void setToken_sender(String token_sender) {
            this.token_sender = token_sender;
        }

        public String getName_sender() {
            return name_sender;
        }

        public void setName_sender(String name_sender) {
            this.name_sender = name_sender;
        }

        public String getToken_receiver() {
            return token_receiver;
        }

        public void setToken_receiver(String token_receiver) {
            this.token_receiver = token_receiver;
        }

        public String getName_receiver() {
            return name_receiver;
        }

        public void setName_receiver(String name_receiver) {
            this.name_receiver = name_receiver;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public List<Datachats> getDatachats() {
        return datachats;
    }

    public void setDatachats(List<Datachats> datachats) {
        this.datachats = datachats;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public class History{
        private String token_sender;
        private String name_sender;
        private String token_receiver;
        private String name_receiver;
        private String date;
        private String time;
        private String message;

        public String getToken_sender() {
            return token_sender;
        }

        public void setToken_sender(String token_sender) {
            this.token_sender = token_sender;
        }

        public String getName_sender() {
            return name_sender;
        }

        public void setName_sender(String name_sender) {
            this.name_sender = name_sender;
        }

        public String getToken_receiver() {
            return token_receiver;
        }

        public void setToken_receiver(String token_receiver) {
            this.token_receiver = token_receiver;
        }

        public String getName_receiver() {
            return name_receiver;
        }

        public void setName_receiver(String name_receiver) {
            this.name_receiver = name_receiver;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public class Users{
        private String token;
        private String name;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Checkuser getCheckuser() {
        return checkuser;
    }

    public void setCheckuser(Checkuser checkuser) {
        this.checkuser = checkuser;
    }

    public class Checkuser{
        private String status;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
