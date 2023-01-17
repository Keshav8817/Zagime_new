import React, { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
} from "@mui/material";
import { Link } from "react-router-dom";
import { grey } from "@mui/material/colors";

import moment from "moment";
import { getAllAppointmentByDate } from "./service";
import { AppointmentData } from "./CalendarDataType";

const AppointmentEvent: any = (props: any) => {
  const [data, setData] = useState<AppointmentData[]>([
    {
      appointmentId: 0,
      subject: "",
      status: "",
      date: "",
      participantId: 0,
      icAppointmentId: 0,
      cgProviderId: 0,
    },
  ]);
  useEffect(() => {
    if (props.date) {
      getAllAppointmentByDate(props.date).then(({ data }) => {
        setData(data);
      });
    }
  }, [props.date]);

  useEffect(() => {
    getAllAppointmentByDate(moment(new Date()).format("yyyy-MM-DD")).then(
      ({ data }) => {
        setData(data);
      }
    );
  }, []);

  const handleSelected = (id: number, participant: number) => {};
  return (
    <div>
      <Table sx={{ minWidth: 600 }} aria-label="simple table">
        <TableHead>
          <TableRow sx={{ backgroundColor: "orange", color: "white" }}>
            <TableCell
              sx={{ color: "white" }}
              align="center"
              size="small"
            ></TableCell>
            <TableCell sx={{ color: "white" }} align="center" size="small">
              Date
            </TableCell>
            <TableCell sx={{ color: "white" }} align="center" size="small">
              Subject
            </TableCell>
            <TableCell sx={{ color: "white" }} align="center" size="small">
              Status
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody
          sx={{
            "& > tr > td": {
              backgroundColor: grey["400"],
              p: "0.25rem",
            },
            "& > tr": { border: 0 },
          }}
        >
          {data.map((val: any, key: any) => (
            <TableRow
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
              }}
            >
              <TableCell sx={{ color: "black" }} align="center" size="small">
                {val.participantId && (
                  <Link
                    to={`../cyfms/appointment/${val.participantId}`}
                    onClick={() => {
                      localStorage.setItem("filedetailsId", val.fileDetailsId);
                    }}
                  >
                    Select
                  </Link>
                )}

                {val.fileDetailsId && (
                  <Link
                    to={`../initial_contact/appointment/${val.fileDetailsId}`}
                    onClick={() => {
                      localStorage.setItem("filedetailsId", val.fileDetailsId);
                    }}
                  >
                    Select
                  </Link>
                )}
              </TableCell>
              <TableCell sx={{ color: "black" }} align="center" size="small">
                {val.date}
              </TableCell>
              <TableCell sx={{ color: "black" }} align="center" size="small">
                {val.subject}
              </TableCell>
              <TableCell sx={{ color: "black" }} align="center" size="small">
                {val.status}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default AppointmentEvent;
