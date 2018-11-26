DROP TABLE IF EXISTS communication;
DROP TABLE IF EXISTS file;
DROP TABLE IF EXISTS customer_offer;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS offer;

CREATE TABLE IF NOT EXISTS customer (
  customer_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  mob_phone varchar(20) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  skills varchar(2048) DEFAULT NULL,
  additional_info varchar(4096) DEFAULT NULL,
  mod_date DATETIME DEFAULT NULL,
  PRIMARY KEY (customer_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS file (
  file_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  content varchar(20000) DEFAULT NULL,
  file_type tinyint DEFAULT NULL,
  mod_date DATETIME DEFAULT NULL,
  PRIMARY KEY (file_id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS communication (
  communication_id int(11) NOT NULL AUTO_INCREMENT,
  file_id int(11) NOT NULL,
  customer_id int(11) NOT NULL,
  mail_sended BOOLEAN DEFAULT NULL,
  sms_sended BOOLEAN DEFAULT NULL,
  mod_date DATETIME DEFAULT NULL,
  PRIMARY KEY (communication_id),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`file_id`) REFERENCES `file` (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS offer (
  offer_id int(11) NOT NULL AUTO_INCREMENT,
  offer_type tinyint DEFAULT NULL,
  title varchar(4096) DEFAULT NULL,
  text varchar(8192) DEFAULT NULL,
  mod_date DATETIME DEFAULT NULL,
  image_path varchar(4096) DEFAULT NULL,
  PRIMARY KEY (offer_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS customer_offer (
  customer_id int(11) NOT NULL,
  offer_id int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`offer_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_offer_ibfk_1`
   FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `customer_offer_ibfk_2`
   FOREIGN KEY (`offer_id`) REFERENCES `offer` (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS logs (
  log_id int(11) NOT NULL AUTO_INCREMENT,
  log_type tinyint DEFAULT NULL,
  user_agent varchar(128) DEFAULT NULL,
  method varchar(128) DEFAULT NULL,
  ip varchar(128) DEFAULT NULL,
  PRIMARY KEY (log_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO offer (offer_id, offer_type, title, text, mod_date, image_path)
VALUES (1, 1, 'W&#243;zki wid&#322;owe', 'Oferta pracy dla w&#243;zk&#243;w wid&#322;owych', NOW(), '../../assets/images/wozekWidlowy.jpg');

INSERT INTO offer (offer_id, offer_type, title, text, mod_date, image_path)
VALUES (2, 2, '&#379;urawie', 'Oferta pracy dla &#380;urawi wie&#380;owych', NOW(), '../../assets/images/zuraw.jpg');

INSERT INTO offer (offer_id, offer_type, title, text, mod_date, image_path)
VALUES (3, 3, 'Koparki', 'Oferta pracy dla koparek', NOW(), '../../assets/images/koparka.jpg');