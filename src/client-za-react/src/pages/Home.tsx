import {
  Box,
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Typography,
} from "@mui/material";
import { Link } from "react-router-dom";

import React from "react";
import type { FC, ReactNode } from "react";
import HomeLayout from "../components/auth/layout/HomeLayout";

interface IconType {
  value: string;
  route: string;
  image: string;
  color: string;
}

const icons: IconType[] = [
  {
    value: "CHILDREN, YOUTH AND FAMILIES",
    route: "/cyfms",
    image: "https://gigastore.blob.core.windows.net/images/img/cyfi.png",
    color: "blue",
  },
  {
    value: "INITIAL CONTACTS AND REFERRALS",
    route: "/initial_contact",
    image: "https://gigastore.blob.core.windows.net/images/img/ic.png",
    color: "#f3b300",
  },
  {
    value: "FAMILY HEALING",
    route: "/fh",
    image: "https://gigastore.blob.core.windows.net/images/img/fh.png",
    color: "green",
  },
  {
    value: "CULTURAL PROGRAMS AND ACTIVITIES",
    route: "/cpa",
    image: "https://gigastore.blob.core.windows.net/images/img/cp.png",
    color: "red",
  },
  {
    value: "HUMAN RESOURCES",
    route: "/hr",
    image: "https://gigastore.blob.core.windows.net/images/img/hr.png",
    color: "orange",
  },
];

const RenderIcons = (icons: IconType[]): ReactNode[] => {
  let result: ReactNode[] = new Array(icons.length);
  for (let index: number = 0; index < icons.length; ++index) {
    result.push(
      <Link
        key={icons[index].value}
        to={icons[index].route}
        style={{ textDecoration: "none" }}
      >
        <Card elevation={0} sx={{ width: 170 }}>
          <CardActionArea>
            <CardMedia
              component="img"
              height="100px"
              image={icons[index].image}
              alt=""
            />
            <CardContent>
              <Typography
                component="h2"
                variant="body1"
                sx={{
                  fontSize: "1rem",
                  textAlign: "center",
                  color: icons[index].color,
                }}
              >
                {icons[index].value}
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      </Link>
    );
  }
  return result;
};

/**
 * `Home` is displayed on `/home` route.
 * @returns `ReactElement`
 */
const Home: FC = () => {
  return (
    <>
      <HomeLayout>
        {/* <div className="md:container"> */}
        <Box
          sx={{
            display: "flex",
            justifyContent: "center",
            // border: "solid 2px",
            margin: "0px 1px ",
            paddingBottom: "90px",
          }}
        >
          <Box
            sx={{
              paddingTop: "25px",
              display: "flex",
              flexWrap: "wrap",
              gap: "4rem 3rem",
              justifyContent: "center",
              maxWidth: "auto",
            }}
          >
            {RenderIcons(icons)}
          </Box>
        </Box>
        {/* </div> */}
      </HomeLayout>
    </>
  );
};

export default Home;
