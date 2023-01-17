CREATE TABLE `USERS` (
    `enabled` BOOLEAN,
    `first_name` VARCHAR(65) NOT NULL,
    `sur_name` VARCHAR(65) NOT NULL,
    `middle_name` VARCHAR(65),
    `username` VARCHAR(65) NOT NULL,
    `email` VARCHAR(254),
    `password` VARCHAR(65),
    `dateOfBirth` DATE,
    `creation_date` TIMESTAMP NOT NULL,
    `last_access_date` TIMESTAMP NOT NULL,
    `updated_at` TIMESTAMP,
    `profile_type` VARCHAR(12),
    `title` VARCHAR(12),
    `status` VARCHAR(12),
    `phone` VARCHAR(20),
    `location` VARCHAR(12),
    `role` VARCHAR(12),
    `preferred_language` VARCHAR(12),
    PRIMARY KEY (`username`)
);

CREATE TABLE `BlacklistedTokens` (
     `users_id` bigint(20) NOT NULL,
     `jwt_token` TEXT,
     `blacklisted_date` DATE,
     PRIMARY KEY (`users_id`)
);

ALTER TABLE `BlacklistedTokens`
    ADD FOREIGN KEY (`username`) REFERENCES `USERS`(`username`);
ALTER TABLE `USERS` DROP `users_id`;
ALTER TABLE `BlacklistedTokens` DROP `users_id`;
ALTER TABLE `BlacklistedTokens` DROP FOREIGN KEY(`users_id`);
ALTER TABLE `USERS` ADD PRIMARY KEY(`username`);
ALTER TABLE `BlacklistedTokens` ADD `username` VARCHAR(65) NOT NULL;

INSERT INTO za.USERS
(enabled, first_name, sur_name, middle_name, username, email, password, date_of_irth, creation_date, last_access_date, updated_at,
 profile_type, title, status, phone, location, `role`, preferred_language)
VALUES(true, 'Lav', 'Pandey', 'Kumar', 'lav', 'lav@test.com', '', '2022-05-29', '2022-05-29', '2022-05-29', '2022-05-29',
       'CASEWORKER', 'MR', 'ACTIVE', '6472072779', 'TORONTO', 'CASEWORKER', 'ENGLISH');
-------------------------------------------------
CREATE TABLE `shareholdingpattern` (
                                       `securityCode` bigint(20) NOT NULL,
                                       `promotersPrcnt` double DEFAULT NULL,
                                       `pledgedSharesPrcnt` double DEFAULT NULL,
                                       `totalSharesInCr` double DEFAULT NULL,
                                       `datePopulated` date DEFAULT NULL,
                                       `quarterID` varchar(50) NOT NULL,
                                       `quarterName` varchar(50) DEFAULT NULL,
                                       PRIMARY KEY (`securityCode`,`quarterID`)
);

CREATE TABLE `mywatchlist` (
                               `securityCode` bigint(20) NOT NULL,
                               `securityName` varchar(200) NOT NULL,
                               `industry` varchar(500) NOT NULL,
                               PRIMARY KEY (`securityCode`)
);

CREATE TABLE `stockcurrentmarketprice` (
                                           `securityCode` bigint(20) NOT NULL,
                                           `closePrice` double NOT NULL,
                                           `date` date NOT NULL,
                                           `daysHigh` double NOT NULL,
                                           `daysLow` double NOT NULL,
                                           `noOfTrades` double NOT NULL,
                                           `noOfShares` double NOT NULL,
                                           PRIMARY KEY (`securityCode`,`date`)
);

CREATE TABLE `financialyearprofitlossrecord` (
                                                 `securityCode` bigint(20) NOT NULL,
                                                 `financialYear` int(11) NOT NULL,
                                                 `income` double DEFAULT NULL,
                                                 `otherIncome` double DEFAULT NULL,
                                                 `totalIncome` double DEFAULT NULL,
                                                 `expenditure` double DEFAULT NULL,
                                                 `interest` double DEFAULT NULL,
                                                 `depreciation` double DEFAULT NULL,
                                                 `tax` double DEFAULT NULL,
                                                 `netProfit` double DEFAULT NULL,
                                                 PRIMARY KEY (`securityCode`,`financialYear`)
);

CREATE TABLE `quarterlyprofitlossrecord` (
                                             `securityCode` bigint(20) NOT NULL,
                                             `quarterFY` int(11) NOT NULL,
                                             `income` double DEFAULT NULL,
                                             `otherIncome` double DEFAULT NULL,
                                             `totalIncome` double DEFAULT NULL,
                                             `expenditure` double DEFAULT NULL,
                                             `interest` double DEFAULT NULL,
                                             `depreciation` double DEFAULT NULL,
                                             `tax` double DEFAULT NULL,
                                             `netProfit` double DEFAULT NULL,
                                             PRIMARY KEY (`securityCode`,`quarterFY`)
);

CREATE TABLE `financialyearbalancesheetrecord` (
                                                   `securityCode` bigint(20) NOT NULL,
                                                   `financialYear` int(11) NOT NULL,
                                                   `sahreCapital` double DEFAULT NULL,
                                                   `reserves` double DEFAULT NULL,
                                                   `totalCurrentLiabilities` double DEFAULT NULL,
                                                   `totalNonCurrentLiabilities` double DEFAULT NULL,
                                                   `totalCurrentAssets` double DEFAULT NULL,
                                                   `totalNonCurrentAssets` double DEFAULT NULL,
                                                   PRIMARY KEY (`securityCode`,`financialYear`)
);

CREATE TABLE `financialyearcashflowrecord` (
                                               `securityCode` bigint(20) NOT NULL,
                                               `financialYear` int(11) NOT NULL,
                                               `cashflowFromOperatingActivities` double NOT NULL,
                                               `cashflowFromFinancingActivities` double NOT NULL,
                                               `cashflowFromInvestingActivities` double NOT NULL,
                                               `capex` double NOT NULL,
                                               `netCashFlow` double NOT NULL,
                                               PRIMARY KEY (`securityCode`,`financialYear`)
);

CREATE TABLE `financialyearhighlowperecord` (
                                                `securityCode` bigint(20) NOT NULL,
                                                `financialYear` int(11) NOT NULL,
                                                `lowPE` double NOT NULL,
                                                `highPE` double NOT NULL,
                                                PRIMARY KEY (`securityCode`,`financialYear`)
);

CREATE TABLE `financialratiosrecord` (
                                         `securityCode` bigint(20) NOT NULL,
                                         `financialYear` int(11) NOT NULL,
                                         `roe` double NOT NULL,
                                         `roce` double NOT NULL,
                                         `roic` double NOT NULL,
                                         `npm` double NOT NULL,
                                         PRIMARY KEY (`securityCode`,`financialYear`)
);

CREATE TABLE `StockSplitInfo` (
                                  `securityCode` bigint(20) NOT NULL,
                                  `executionDate` date NOT NULL,
                                  `purpose` varchar(200) NOT NULL,
                                  `divideOldStockPrice` double NOT NULL,
                                  PRIMARY KEY (`securityCode`,`executionDate`)
);

CREATE TABLE `CurrentPortfolio` (
                                    `securityCode` bigint(20) NOT NULL,
                                    `transactionDate` date NOT NULL,
                                    `transactionPrice` double NOT NULL,
                                    `targetPriceToSell` double NOT NULL,
                                    `quantity` double NOT NULL,
                                    `soldPrice` double NOT NULL,
                                    PRIMARY KEY (`securityCode`,`transactionDate`)
);

CREATE TABLE `Top500Stcoks` (
                                `securityCode` bigint(20) NOT NULL,
                                `reasonToPick` varchar(1000) NOT NULL,
                                PRIMARY KEY (`securityCode`)
);
CREATE TABLE `MarqueeInvestor` (
                                   `investorID` int(11) NOT NULL,
                                   `investorName` varchar(200) NOT NULL,
                                   PRIMARY KEY (`investorID`)
);
CREATE TABLE `MarqueeInvestorHoldings` (
                                           `securityCode` bigint(20) NOT NULL,
                                           `investorID` int(100) NOT NULL,
                                           PRIMARY KEY (`securityCode`, `investorID`)
);

CREATE TABLE BulkDeals(
                          bulkDealsID bigint NOT NULL AUTO_INCREMENT,
                          securityId varchar(50) NOT NULL,
                          date date DEFAULT NULL,
                          clientName varchar(200) NULL,
                          quantityTraded bigint NULL,
                          tradePrice double NULL,
                          action varchar(20) NULL,
                          exchange varchar(20) NULL,
                          PRIMARY KEY (bulkDealsID)
);

ALTER TABLE BulkDeals RENAME COLUMN action TO dealType;

CREATE TABLE DailyBhavCopy (
                               exchange varchar(50) DEFAULT NULL,
                               bhavcopyjson JSON,
                               datePopulated date DEFAULT NULL
);

=============================================================================================

ALTER TABLE bseallgroupsstocks RENAME TO ListedUniverse;
ALTER TABLE ListedUniverse ADD ISIN varchar(255) NOT NULL;
ALTER TABLE ListedUniverse MODIFY ISIN ISIN varchar(255) NOT NULL;
ALTER TABLE ListedUniverse DROP PRIMARY KEY, ADD PRIMARY KEY(ISIN);
ALTER TABLE ListedUniverse DROP PRIMARY KEY;
ALTER TABLE ListedUniverse ADD PRIMARY KEY (ISIN);
ALTER TABLE ListedUniverse ADD CONSTRAINT PK_ISIN PRIMARY KEY (ISIN);
ALTER TABLE ListedUniverse DROP securityId;
ALTER TABLE ListedUniverse DROP securityCode;
ALTER TABLE ListedUniverse DROP industry;

ALTER TABLE ListedUniverse ADD bsesecurityId bigint(20);
ALTER TABLE ListedUniverse ADD bsesymbol varchar(50);
ALTER TABLE ListedUniverse ADD nsesymbol varchar(50);
ALTER TABLE ListedUniverse ADD industry varchar(500);
ALTER TABLE ListedUniverse ADD dateLoaded date DEFAULT NULL;


ALTER TABLE BulkDeals ADD bsesymbol varchar(50);
ALTER TABLE BulkDeals ADD nsesymbol varchar(50);
ALTER TABLE BulkDeals ADD ISIN varchar(255) NOT NULL;

CREATE TABLE SecurityWiseDeliveryPosition (
                                              isin varchar(255) NOT NULL,
                                              nsesymbol varchar(50),
                                              bsesymbol varchar(50),
                                              datepopulated date DEFAULT NULL,
                                              quantityTraded double NULL,
                                              deliverableQuantity double NULL,
                                              prcntOfDlvQtyToTrdQty double NULL
);

CREATE TABLE DailyBhav (
                           isin varchar(255) NOT NULL,
                           nsesymbol varchar(50),
                           bsesymbol varchar(50),
                           datepopulated date NOT NULL,
                           openprice double NULL,
                           highprice double NULL,
                           lowprice double NULL,
                           closeprice double NULL,
                           lastprice double NULL,
                           totalTradedQty double NULL,
                           totalTradedVol double NULL,
                           totaltrades double NULL,
                           corporateActionsIndicators varchar(50) NOT NULL,
                           PRIMARY KEY (`isin`,`datepopulated`)
);

ALTER TABLE listeduniverse ADD nsesecurityname varchar(200);

ALTER TABLE clonetarget ADD synonyms varchar(255) NOT NULL;

ALTER TABLE fy_balance_sheet ADD inventory_turnover double NULL;
ALTER TABLE fy_balance_sheet ADD roe double NULL;
ALTER TABLE fy_balance_sheet ADD roce double NULL;



ALTER TABLE fy_balance_sheet ADD financialYear int(6) NOT NULL;
ALTER TABLE fy_balance_sheet ADD isin varchar(255) NOT NULL;

ALTER TABLE fy_balance_sheet ADD PRIMARY KEY (isin, financialYear);
