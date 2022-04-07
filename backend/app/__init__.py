from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
from config import config

db = SQLAlchemy()

def create_app(app_config='development'):
    app = Flask(__name__)
    app.config.from_object(config[app_config])

    from app.main.routes import main

    app.register_blueprint(main)
    db.init_app(app)
    return app


