

-- zuerst die Datenbank erstellen
DROP DATABASE IF EXISTS webshop;
CREATE DATABASE webshop;
-- wenn das Backend am laufen ist, kann man den unteren Code ausfueren.

use webshop;

INSERT INTO category(id, category) VALUES 
(1, "Clothing"),
(2, "Tv & Audio"),
(3, "Toy"),
(4, "Tools"),
(5, "Shoes"),
(6, "Computer & Gaming"),
(7, "Houshold & Kitchen"),
(8, "Beauty & Health"),
(9, "Sport"),
(10, "Office");


INSERT INTO products(id, content, description, price, published, src, stock, title, category_id) VALUES 
(1, "The Air Force 1 first appeared in 1982, bringing basketball shoes from the hardwood floor to the tarmac. It was the first basketball sneaker with Nike Air.",
"UI/UX designing, new", 238, true, null, 40, "Air Force 1", 5),
(2, "Personalized 3D audio with dynamic head tracking delivers sound all around you",
"with Lightning charging case​​​​​(2022) ", 120, false, null, 2, "Apple AirPods (3. Generation)", 2),
(3, "With the DJI Mini 2 (4K) drone you can enjoy a range of 10km and should let off steam if you already have a bit of an idea. It is best to start with the DJI Mini 2 (4K) drone from the age of 16.",
"(4K, Full HD, 2.7K)", 405, true, null, 20, "DJI Mini 2", 3);


INSERT INTO newslatter(id, email_id) VALUES 
(1, "test@me.com"),
(2, "scary@gamail.com");



SELECT * FROM test.newslatter;
SELECT * FROM webapp.products;
SELECT * FROM webapp.category;

