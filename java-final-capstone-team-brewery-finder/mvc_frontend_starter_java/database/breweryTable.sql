BEGIN TRANSACTION;

DROP TABLE IF EXISTS brewery    CASCADE;
drop sequence if exists seq_brewery_id;
create sequence seq_brewery_id;

CREATE TABLE brewery (
        brewery_id integer not null DEFAULT NEXTVAL('seq_brewery_id'),
        brewery_name varchar(30) NOT NULL,
        street_address varchar(75) NOT NULL,
        web_address varchar(50) NOT NULL,
        phone_number varchar(15) NOT NULL,
        hours_of_operation varchar(100) NOT NULL,
        history varchar(50) NOT NULL,
        CONSTRAINT pk_brewery_brewery_id PRIMARY KEY (brewery_id)
);

commit;

INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Great Lakes Brewing Company', '2516 Market Avenue Cleveland OH 44113', 'greatlakesbrewing.com', '216-771-4404', 'Mon-Thurs 11:30am-10pm Fri 11:30am-11pm Sat 11am-11pm Sun 11am-5pm', 'Est. 1988');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Fat Heads Brewery', '17450 Engle Lake Dr. Middleburg Hts OH 44130', 'fatheads.com', '216-898-0242', 'Mon-Thurs 11am-11pm Fri-Sat 11am-12pm Sun 11am-10pm', 'Est. 2009');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Brick and Barrel Brewing Co.', '1844 Columbus Road Cleveland OH 44113', 'brickandbarrelbrewing.com', '216-331-3308', 'Wed-Thurs 4pm-10pm Fri-Sat 2pm-12am Sun 2pm-8pm', 'Est. 2015');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Noble Beast Brewing Co.', '1470 Lakeside Ave E Cleveland OH 44114', 'noblebeastbeer.com', '216-417-8588', 'Mon-Sun 11:30am-8pm', 'Est. 2017');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Terrestrial Brewing Company', '7524 Father Frascati Cleveland OH 44102', 'facebook.com/terrestrialbrewing', '216-465-9999', 'Mon-Thurs 4pm-11pm Fri 3pm-12am Sat 12pm-1am Sun 12pm-10pm', 'Est. 2017');  
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Bookhouse Brewing', '1526 W 25th Street Cleveland OH 44113', 'bookhouse.beer', '216-862-4048', 'Tues-Thur 4pm-10pm Fri 3pm-12pm Sat 12pm-12am Sun 12pm-8pm', 'Est. 2018');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Forest City Brewery', '2135 Columbus Road Cleveland OH 44113', 'forestcitybrewery.com', '216-228-9116', 'Tues-Thurs 4pm-11pm Fri 4pm-1am Sat 12pm-1am Sun 12pm-8pm', 'Est. 2015');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('The Brew Kettle', '8377 Pearl Road Strongsville OH 44136', 'thebrewkettle.com', '440-239-8788', 'Mon-Sun 10:30am-7:30pm', 'Est. 1995');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
           VALUES('Sibling Revelry Brewing', '29305 Clemens Road Westlake OH 44115', 'siblingrevelrybrewing.com', '440-471-8589', 'Mon-Thurs 4pm-10pm Fri 4-11 Sat 12-11 Sun 12-7', 'Est. 2016');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('The Cleveland Brewery', '777 E 185th Street Cleveland OH 44119', 'theclevelandbrewery.com', '216-534-6992', 'Fri-Sat 6pm-10pm', 'Est. 2014');                      
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Southern Tier Brewing', '811 Prospect Ave E Cleveland OH 44115', 'stbcbeer.com', '440-484-4045', 'Thurs-Sat 11am-8pm', 'Est. 2008');                      
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Masthead Brewing Company', '1261 Superior Ave Cleveland OH 44114', 'mastheadbrewingco.com', '216-206-6176', 'Tues-Sat 12pm-7pm', 'Est. 2017');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Market Garden Brewery', '1947 W 25th St Cleveland OH 44113', 'marketgardenbrewery.com', '216 621-4000', 'Mon-Sun 11am-2am', 'Est. 2011');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Platform Brewery Company', '4125 Lorain Ave Cleveland OH 44113', 'platformbeer.co', '216-202-1386', 'Mon-Sat 11am-8pm', 'Est. 2014');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Goldhorn Brewery', '1361 E 55th St Cleveland OH 44103', 'goldhornbrewery.com', '216-465-1352', 'Tues-Thurs Fri-Sat 11am-9pm Sun 12pm-8pm', 'Est. 2016');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Collision Bend Brewery', '1250 Old River Rd Cleveland OH 44113', 'collisionbendbrewery.com', '216-273-7879', 'Tues-Thurs 3pm-10pm Fri 12pm-12am Sat Sun 12pm-8pm', 'Est. 2016');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Saucy Brew Works', '2885 Detroit Ave Cleveland OH 44113', 'saucybrewworks.com', '216-666-2568', 'Tues-Sun 12pm-8pm', 'Est. 2015');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Nano Brew Cleveland', '1859 W 25th St Cleveland OH 44113', 'nanobrewcleveland.com', '216-862-6631', 'Tues-Sun 11pm-1am', 'Est. 2013');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Voodoo Brewery Cleveland', '2279 Lee Rd Cleveland Heights OH 44118', 'voodoobrewery.com', '216-331-6775', 'Mon-Thurs 3pm-11pm Fri-Sat 11am-12am Sun 12am-8pm', 'Est. 2005');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Rocky River Brewing Co', '21290 Center Ridge Rd Rocky River OH 44116', 'rockyriverbrewco.com', '440-895-2739', 'Mon-Thurs/Sun 11:30am-11pm Fri-Sat 11:30-12am', 'Est. 1998');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Akronym Brewing', '58 E Market St Akron OH 44308', 'akronymbrewing.com', '330-620-8274', 'Mon-Thurs 4pm-7pm Fri-Sat 12pm-6pm Sun 12pm-4pm', 'Est. 2009');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Thirsty Dog Brewing', '587 Grant St Akron OH 44311', 'thirstydog.com/tap-house', '234-571-1456', 'Tues-Thurs 12pm-9pm Fri-Sat 12pm-10pm Sun 12pm-4pm', 'Est. 1997');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Eighty-Three Brewery', '1201 E Market St Suite 110 Akron OH 44305', 'eighty-threebrewery.com', '234.571.1067', 'Mon-Thur 3pm-10pm Fri-Sat 12pm-11pm Sun 12pm-8pm', 'Est. 2019');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Magic City Brewing Company', '2727 Manchester Rd Akron OH 44319', 'magiccitybrewingcompany.com', '234.571.1067', 'Fri 4pm-8pm Sat/Sun 12pm-4pm', 'Est. 2017');
INSERT INTO brewery (brewery_name, street_address, web_address, phone_number, hours_of_operation, history)
            VALUES('Mucky Duck Brewery', '4019 S Main St, Akron, OH 44319', 'muckyduckbrewery.com', '330-644-0137', 'Wed-Fri 4pm-11pm Sat 2pm-11pm', 'Est. 2013');
Commit;