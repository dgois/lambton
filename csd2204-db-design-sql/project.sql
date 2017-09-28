Create Table Company 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null,
BusinessNumber varchar(50) Null,
Email varchar(255) Not Null
);

Create Table Supervisor 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null,
Email varchar(255) Not Null
);

Create Table Country 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null,
Code varchar(255) Not Null
);

Create Table Site 
(
Id int Primary Key Auto_Increment Not Null,
StreetName varchar(255) Not Null,
StreetNumber varchar(255) Not Null,
ZipCode varchar(50) Not Null,
Province varchar(255)  Null,
City varchar(255) Not Null,
Longitude varchar(255) Not Null,
Latitude varchar(255) Not Null,
CountryCodeId int Null,
PhoneNumber varchar(255) Not Null,
Foreign Key(CountryCodeId) References Country(Id)
);

Create Table CompanySite 
(
Id int Primary Key Auto_Increment Not Null,
CompanyId int Not Null,
SiteId int Not Null,
SupervisorId int  Not Null,
Foreign Key(CompanyId) References Company(Id),
Foreign Key(SiteId) References Site(Id),
Foreign Key(SupervisorId) References Supervisor(Id)
);

Create Table SpaceStatus 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null
);

Create Table Category 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null
);

Create Table Space
(
Id int Primary Key Auto_Increment Not Null,
CompanySiteId int Not Null,
CategoryId int Not Null,
SpaceStatusId int Not Null,
Foreign Key (CompanySiteId) References CompanySite(Id),
Foreign Key (CategoryId) References Category(Id),
Foreign Key (SpaceStatusId) References SpaceStatus(Id)
);

Create Table Customer 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null,
Email varchar(255) Not Null,
Phone varchar(255) Not Null,
CountryId int Not Null,
CategoryId int Not Null,
Foreign Key (CategoryId) References Category(Id),
Foreign Key (CountryId) References Country(Id)
);

Create Table Reservation
(
Id int Primary Key Auto_Increment Not Null,
ReservationDate DateTime Default CURRENT_TIMESTAMP Not Null,
CustomerId int Not Null,
ReservationStatus varchar(255) Not Null,
CheckIn Datetime Not Null,
CheckOut DateTime Not Null,
SpaceId int Not Null,
Foreign Key(SpaceId) References Space(Id),
Foreign Key(CustomerId) References Customer(Id)
);

Create Table ReservationFeedback
(
Id int Primary Key Auto_Increment Not Null,
ReservationId int Null,
Rating int Not Null,
Feedback varchar(1000),
FeedbackDate datetime Default CURRENT_TIMESTAMP Not Null,
Foreign Key(ReservationId) References Reservation(Id)
);

Create Table PaymentType
(
Id int Not Null Auto_Increment Primary Key,
Name varchar(255) Not Null
);

Create Table PaymentStatus 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null
);

Create Table Tax 
(
Id int Primary Key Auto_Increment Not Null,
Name varchar(255) Not Null,
Rate Float Not Null
);

Create Table Billing 
(
Id int Primary Key Auto_Increment Not Null,
ReservationId int Not Null,
Amount float Not Null,
BillingDate datetime Not Null,
PaymentTypeId int Not Null,
PaymentStatusId int,
TaxId int Not Null,
Tax float Not Null,
TotalAmount float Not Null,
Foreign Key (TaxId) References Tax(Id),
Foreign Key (ReservationId) References Reservation(Id) ,
Foreign Key (PaymentTypeId) References PaymentType(Id) ,
Foreign Key (PaymentStatusId) References PaymentStatus(Id)
);

--====================Master Table Entry=========================================

Insert into Tax (Name, Rate) values ('No Tax', 0);
Insert into Tax (Name, Rate) values ('HST', 13);
Insert into Tax (Name, Rate) values ('Service Tax', 5);
Insert into Tax (Name, Rate) values ('GST', 18);

Insert into PaymentStatus (Name) values ('Pending');
Insert into PaymentStatus (Name) values ('Done');

Insert into PaymentType (Name) values ('Cash');
Insert into PaymentType (Name) values ('Credit Card');
Insert into PaymentType (Name) values ('Debit Card');

Insert into SpaceStatus (Name) values ('Available');
Insert into SpaceStatus (Name) values ('Not Available');
Insert into SpaceStatus (Name) values ('Waitlist');

Insert into Country (Name,Code) values ('Canada','+1');
Insert into Country (Name,Code) values ('USA','+1');
Insert into Country (Name,Code) values ('India','+91');
Insert into Country (Name,Code) values ('Kuwait','+965');
Insert into Country (Name,Code) values ('Brazil','+55');

Insert into Category (Name) values ('General');
Insert into Category (Name) values ('Premium');
Insert into Category (Name) values ('VVIP');


--=================Company Registration=====================
INSERT INTO Company (Name,BusinessNumber,Email) values ('Tim Hortan','XYZ68170','abx@gmail.com');
INSERT INTO Company (Name,BusinessNumber,Email) values ('DOMINOS','ABC2123','xyz@gmail.com');
INSERT INTO Company (Name,BusinessNumber,Email) values ('PIZZA PIZZA','QWW1213','sass@gmail.com');
INSERT INTO Company (Name,BusinessNumber,Email) values ('STAR RESTAURANT','DAS212','abx@yahoo.com');
INSERT INTO Company (Name,BusinessNumber,Email) values ('CIC PARKING','XZ45465','abx@gmail.com');
INSERT INTO Company (Name,BusinessNumber,Email) values ('CIC HOSPITAL','XYZ68170','abx@gmail.com');


INSERT INTO Supervisor (Name, Email) values ('Abdeali', 'abdeali.jaorawala@gmail.com');
INSERT INTO Supervisor (Name, Email) values ('Denis Willian', 'Denis@yahoo.com');
INSERT INTO Supervisor (Name, Email) values ('Deepak Sagar', 'Deepak.sagar@gmail.com');







 
