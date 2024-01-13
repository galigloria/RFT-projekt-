-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Dec 16. 11:32
-- Kiszolgáló verziója: 10.4.27-MariaDB
-- PHP verzió: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `mystore`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felhasznalo`
--

CREATE TABLE `felhasznalo` (
  `ID` int(10) NOT NULL,
  `NEV` varchar(200) NOT NULL,
  `EMAIL` varchar(200) NOT NULL,
  `TELEFONSZAM` varchar(200) DEFAULT NULL,
  `LAKCIM` varchar(200) DEFAULT NULL,
  `JELSZO` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `felhasznalo`
--

INSERT INTO `felhasznalo` (`ID`, `NEV`, `EMAIL`, `TELEFONSZAM`, `LAKCIM`, `JELSZO`) VALUES
(1, 'Kovács Balázs ', 'kovácsbalazs@gmail.com', '06205634500', 'Kunmadras', 'kovacsbalazs'),
(2, 'GALI Glória ', 'galigloria5@gmail.com', '06300104484', 'Tiszafüred', 'galigloria'),
(3, 'Kovács József ', 'kovacsjozsef@gmail.com', '06300504434', 'Poroszló', 'kovacs'),
(5, 'Nagy Evelin ', 'nagyevelin@gmail.com', '06302134500', 'Budapest', 'nagyevi');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `felhasznalo`
--
ALTER TABLE `felhasznalo`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
