from flask import Flask, render_template, url_for, redirect, request, session, Blueprint
import FCMManager as fcm
import Connect as connect
from datetime import datetime
from flask import request
# from app.Connect import show_users
import mysql.connector
import json

dashboard=Blueprint('dashboard', __name__, url_prefix='/v1/')

@dashboard.route('/')
def dashboard_admin():
    return "You can Access this"

@dashboard.route('/sendchat', methods=['GET', 'POST'])
def get_message():
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)

    token_sender=request.args.get('token_sender')
    name_sender=request.args.get('name_sender')
    message=request.args.get('message')
    token_receiver=request.args.get('token_receiver')
    name_receiver=request.args.get('name_receiver')
    receivers=[token_receiver]

    tanggal = str(datetime.now().strftime("%d/%m/%Y"))
    waktu = str(datetime.now().strftime("%H:%M"))
    connect.insert_chat(token_sender, name_sender,receivers[0], name_receiver, tanggal, waktu, message, db)
    fcm.sendPush("Alfachri", message, receivers)
    data_list={"datachats" : connect.show_chats(token_sender,token_receiver, db)}
    print(type(json.dumps(data_list)))
    return json.dumps(data_list)

@dashboard.route('/datachats', methods=['GET', 'POST'])
def show_chats():
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    sender=request.args.get('sender')
    receiver=request.args.get('receiver')
    data_list={"datachats" : connect.show_chats(sender,receiver, db)}
    return json.dumps(data_list)

@dashboard.route('/register', methods=['GET', 'POST'])
def register_user():
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    token=request.args.get('token')
    name=request.args.get('name')
    connect.insert_user(token, name, db)
    return "Sucessfull Added User"

@dashboard.route('/users', methods=['GET', 'POST'])
def show_users():
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    sender=request.args.get('token')
    data_users=connect.show_users(sender, db)
    return json.dumps({"users":data_users})

@dashboard.route('/checkuser', methods=['GET', 'POST'])
def checkuser():
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    token=request.args.get('token')
    data_users=connect.getAllUsers(token, db)
    data_tokens=[]
    for user in data_users:
        data_tokens.append(user['token'])
    print(data_tokens)
    if token in data_tokens:
        return json.dumps({"checkuser":{"status":"yes"}})
    return json.dumps({"checkuser":{"status":"no"}})

@dashboard.route('/history', methods=['GET', 'POST'])
def show_history():
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    sender=request.args.get('token')
    data_users=connect.show_users(sender, db)
    history_users=[]
    for data in data_users:
        print(data['token'])
        last_chat=connect.history(sender, data['token'], db)
        if (len(last_chat) != 0):
            history_users.append(last_chat)
    return json.dumps({"history":history_users})

# @dashboard.route('/history', methods=['GET', 'POST'])
# def show_history():
#     db = mysql.connector.connect(
#         host="srv51.niagahoster.com",
#         user="u5572326_adi",
#         passwd="Adiadinew1.",
#         database="u5572326_chatapp",
#         port=3306)
#     sender=request.args.get('token')
#     data_users=connect.show_users(sender, db)
#     history_users=[]
#     for data in data_users:
#         last_chat=getLastChat(sender, data['token'])
#         if (last_chat):
#             data['lastchat']=last_chat['message']
#             data['lasttime']=last_chat['time']
#             data['lastdate']=last_chat['date']
#             history_users.append(data)
#     return json.dumps({"history":history_users})



def getLastChat(sender, receiver):
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    datachats=connect.show_chats(sender,receiver, db)
    if len(datachats) ==0:
        return False
    return datachats[len(datachats)-1]

def getLastTime(sender, receiver):
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    datachats=connect.show_chats(sender,receiver, db)
    if len(datachats) ==0:
        return False
    return datachats[len(datachats)-1]['time']

def getLastDate(sender, receiver):
    db = mysql.connector.connect(
        host="srv51.niagahoster.com",
        user="u5572326_adi",
        passwd="Adiadinew1.",
        database="u5572326_chatapp",
        port=3306)
    datachats=connect.show_chats(sender,receiver, db)
    if len(datachats) ==0:
        return False
    return datachats[len(datachats)-1]['date']
    

