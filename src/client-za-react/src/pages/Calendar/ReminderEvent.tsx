import React, { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  useMediaQuery,
  useTheme,
} from "@mui/material";
import { Link } from "react-router-dom";
import { grey } from "@mui/material/colors";

import moment from "moment";
import { ReminderData } from "./CalendarDataType";
import { getAllReminderByDate } from "./service";

const ReminderEvent: any = (props: any) => {
  const theme = useTheme();

  const isMatch = useMediaQuery(theme.breakpoints.down("sm"));
  const [data, setData] = useState<ReminderData[]>([
    {
      reminderId: 0,
      subject: "",
      status: "",
      reminderDate: "",
      participantId: 0,
      fileDetailsId: 0,
      cgProviderId: 0,
    },
  ]);
  useEffect(() => {
    if (props.date) {
      getAllReminderByDate(props.date).then(({ data }) => {
        setData(data);
      });
    }
  }, [props.date]);
  useEffect(() => {
    getAllReminderByDate(moment(new Date()).format("yyyy-MM-DD")).then(
      ({ data }) => {
        setData(data);
      }
    );
  }, []);

  const handleSelected = (id: number, participant: number) => {};
  return (
    <div>
      {isMatch ? (
        <>
          <Table sx={{ width: 380 }} aria-label="simple table">
            <TableHead>
              <TableRow sx={{ backgroundColor: "orange", color: "white" }}>
                <TableCell
                  sx={{ color: "white", width: 10 }}
                  align="center"
                  size="small"
                ></TableCell>
                <TableCell
                  sx={{ color: "white", width: 10 }}
                  align="center"
                  size="small"
                >
                  Date
                </TableCell>
                <TableCell
                  sx={{ color: "white", width: 10 }}
                  align="center"
                  size="small"
                >
                  Subject
                </TableCell>
                <TableCell
                  sx={{ color: "white", width: 10 }}
                  align="center"
                  size="small"
                >
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
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.participantId && (
                      <Link
                        to={`../cyfms/reminder/${val.participantId}`}
                        onClick={() => {}}
                      >
                        Select
                      </Link>
                    )}

                    {val.fileDetailsId && (
                      <Link
                        to={`../initial_contact/reminder/${val.fileDetailsId}`}
                        onClick={() => {
                          localStorage.setItem(
                            "filedetailsId",
                            val.fileDetailsId
                          );
                        }}
                      >
                        Select
                      </Link>
                    )}
                  </TableCell>
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.reminderDate}
                  </TableCell>
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.subject}
                  </TableCell>
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.status}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </>
      ) : (
        <>
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
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.participantId && (
                      <Link
                        to={`../cyfms/reminder/${val.participantId}`}
                        onClick={() => {}}
                      >
                        Select
                      </Link>
                    )}

                    {val.fileDetailsId && (
                      <Link
                        to={`../initial_contact/reminder/${val.fileDetailsId}`}
                        onClick={() => {
                          localStorage.setItem(
                            "filedetailsId",
                            val.fileDetailsId
                          );
                        }}
                      >
                        Select
                      </Link>
                    )}
                  </TableCell>
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.reminderDate}
                  </TableCell>
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.subject}
                  </TableCell>
                  <TableCell
                    sx={{ color: "black" }}
                    align="center"
                    size="small"
                  >
                    {val.status}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </>
      )}
    </div>
  );
};

export default ReminderEvent;
