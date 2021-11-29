import mysql.connector

db = mysql.connector.connect(
  host="srv51.niagahoster.com",
  user="u5572326_adi",
  passwd="Adiadinew1.",
  database="u5572326_chatapp",
  port=3306
)

# if db.is_connected():
#   print("Berhasil terhubung ke database")

# cursor = db.cursor()
# sql = """CREATE TABLE chats (
#   chat_id INT AUTO_INCREMENT PRIMARY KEY,
#   token_sender VARCHAR(255),
#   name_sender VARCHAR(255),
#   token_receiver Varchar(255),
#   name_receiver Varchar(255),
#   chat_date Varchar(255),
#   chat_timr Varchar(255),
#   chat_message Varchar(255)
# )
# """
# cursor.execute(sql)

def insert_chat(toke_sender, name_sender, token_receiver, name_receiver, chat_date, chat_time, chat_message, db):
    cursor = db.cursor()
    sql = "INSERT INTO chats (token_sender, name_sender, token_receiver, name_receiver, chat_date, chat_time, chat_message) VALUES (%s, %s, %s, %s, %s, %s, %s)"
    val = (toke_sender, name_sender, token_receiver, name_receiver, chat_date, chat_time, chat_message)
    cursor.execute(sql, val)
    db.commit()
    print("{} data ditambahkan".format(cursor.rowcount))

def show_chats(chat_sender, chat_receiver, db):
    cursor =db.cursor()
    sql= "SELECT * FROM chats WHERE (token_sender='"+chat_sender+"' AND token_receiver='"+chat_receiver+"') OR (token_sender='"+chat_receiver+"' AND token_receiver='"+chat_sender+"') "
    cursor.execute(sql)
    results=cursor.fetchall()
    mychats=[]

    if cursor.rowcount < 0:
        mychats=[]
    else:
        for data in results:
            data_chat={}
            data_new=list(data)
            data_chat["token_sender"]=data_new[1]
            data_chat["name_sender"]=data_new[2]
            data_chat["token_receiver"]=data_new[3]
            data_chat["name_receiver"]=data_new[4]
            data_chat["date"]=data_new[5]
            data_chat["time"]=data_new[6]
            data_chat["message"]=data_new[7]
            mychats.append(data_chat)
    return mychats

def history(chat_sender, chat_receiver, db):
    cursor =db.cursor()
    sql= "SELECT * FROM chats WHERE (token_sender='"+chat_sender+"' AND token_receiver='"+chat_receiver+"') OR (token_sender='"+chat_receiver+"' AND token_receiver='"+chat_sender+"') ORDER BY chat_id DESC LIMIT 1 "
    cursor.execute(sql)
    results=cursor.fetchone()

    if cursor.rowcount < 0:
        data_chat={}
    else:
        data_chat={}
        data_new=list(results)
        data_chat["token_sender"]=data_new[1]
        data_chat["name_sender"]=data_new[2]
        data_chat["token_receiver"]=data_new[3]
        data_chat["name_receiver"]=data_new[4]
        data_chat["date"]=data_new[5]
        data_chat["time"]=data_new[6]
        data_chat["message"]=data_new[7]
    return data_chat


def insert_user(user_token, user_name, db):
    cursor = db.cursor()
    sql = "INSERT INTO users (token, name) VALUES (%s, %s)"
    val = (user_token, user_name)
    cursor.execute(sql, val)
    db.commit()
    print("{} data ditambahkan".format(cursor.rowcount))


def show_users(token_sender,db):
    cursor =db.cursor()
    sql= "SELECT * FROM users"
    cursor.execute(sql)
    results=cursor.fetchall()
    myusers=[]

    if cursor.rowcount < 0:
        myusers=[]
    else:
        for data in results:
            data_user={}
            data_new=list(data)
            if data_new[1]!=token_sender:
                data_user["token"]=data_new[1]
                data_user["name"]=data_new[2]
                myusers.append(data_user)
    return myusers

def getAllUsers(token_sender,db):
    cursor =db.cursor()
    sql= "SELECT * FROM users"
    cursor.execute(sql)
    results=cursor.fetchall()
    myusers=[]

    if cursor.rowcount < 0:
        myusers=False
    else:
        for data in results:
            data_user={}
            data_new=list(data)
            data_user["token"]=data_new[1]
            data_user["name"]=data_new[2]
            myusers.append(data_user)
    return myusers


# db = mysql.connector.connect(
#         host="srv51.niagahoster.com",
#         user="u5572326_adi",
#         passwd="Adiadinew1.",
#         database="u5572326_chatapp",
#         port=3306)
# print(history("user2", "A1228F", db))


# insert_user("Adi", "Adi")
# insert_user("Alfachri", "Alfachri")

# print(show_users("A1224B", db))
# print(show_chats("Adi", "Alfachri"))