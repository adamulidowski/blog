SET character_set_results = 'utf8', character_set_client = 'utf8', character_set_connection = 'utf8', character_set_database = 'utf8', character_set_server = 'utf8'

ALTER DATABASE `blog` CHARACTER SET utf8 COLLATE utf8_general_ci;

ALTER TABLE `posts` CONVERT TO CHARACTER SET utf8;

-- INSERT INTO `posts`(`Id_post`, `Tittle`, `Date`, `Content`) VALUES ('1', "Tytuł testowy",'2017-02-02 05:05:10', "casasad sadasdashjbdas dadsadb asbdsbd asndb asd asdhjvasb djsabd hjsbadha bd abs basdb sakdb asd a")