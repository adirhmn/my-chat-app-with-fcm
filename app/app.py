from flask import Flask
# from DashboardApi import dashboard
from DashboardApi import dashboard

app=Flask(__name__)
app.config["SECRET_KEY"]="MyChatApp"


app.register_blueprint(dashboard)

if __name__ == "__main__":
    app.run(debug=True, host='192.168.1.101')