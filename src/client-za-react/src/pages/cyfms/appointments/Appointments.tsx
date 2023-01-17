import SearchIcon from "@mui/icons-material/Search";
import {
  Box,
  Button,
  OutlinedInput,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
} from "@mui/material";
import { grey } from "@mui/material/colors";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import type { FC } from "react";
import { searchAppointments } from "./services";
import { Data } from "./AppointmentsDatatypes";
import CYFMSLayout from "../../../components/cyfms/CYFMSLayout";

/**
 * `IC` aka `Initial Contact` module.
 * Sub page: `Appointments`.
 */
const Appointments: FC = () => {
  const { id } = useParams();
  const [addNew, setAddNew] = useState(false);
  const [disabled, setDisabled] = useState(false);
  const [value, setValue] = useState("");
  const [state, setState] = useState<Data>({
    participantAppointmentId: 0,
    participantId: Number(id),
    referenceId: 0,
    appointmentdto: {
      appointmentId: 0,
      subject: "",
      status: "",
      date: "",
      time: "",
      location: "",
      duration: "",
      client: "",
      caseworker: "",
      recurringAppointment: "",
      frequency: "",
      endDate: "",
      notes: "",
    },
  });
  const [data, setData] = useState<Data[]>([
    {
      participantAppointmentId: 0,
      participantId: Number(id),
      referenceId: 0,
      appointmentdto: {
        appointmentId: 0,
        subject: "",
        status: "",
        date: "",
        time: "",
        location: "",
        duration: "",
        client: "",
        caseworker: "",
        recurringAppointment: "",
        frequency: "",
        endDate: "",
        notes: "",
      },
    },
  ]);
  useEffect(() => {
    searchAppointments(Number(id), value).then(({ data }) => {
      setData(data);
    });
  }, [addNew]);

  const handleAddNew = () => {
    // dispatch(cleanState(null));
    setDisabled(false);
    setAddNew(true);
  };

  const handleSelected = (id: number) => {
    // dispatch(doGet(id))
    //   .unwrap()
    // .then(() => {
    setDisabled(true);
    setAddNew(true);
    // });
  };

  const handleSearchIcon = (e: any) => {
    searchAppointments(state.participantId, value).then(({ data }) => {
      setData(data);
    });
  };

  const handleChange = (e: any) => {
    setValue(e.target.value);
  };

  return (
    <CYFMSLayout>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
      >
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ paddingLeft: "8px", flexBasis: 0, flexGrow: 2 }}>
            <OutlinedInput
              id="search"
              value={value}
              placeholder="search..."
              size="small"
              onChange={(e) => handleChange(e)}
              sx={{ borderRadius: 0, flexBasis: 0, flexGrow: 2, ml: -1 }}
              type="text"
              endAdornment={<SearchIcon onClick={(e) => handleSearchIcon(e)} />}
            />
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 2 }}>
            <Button
              sx={{ backgroundColor: "#ffbf00" }}
              variant="contained"
              onClick={() => {
                setValue("");
              }}
            >
              Clear Search
            </Button>
          </Box>
          <Box sx={{ flexBasis: 0, flexGrow: 2 }}></Box>
          <Box
            sx={{
              flexBasis: 0,
              flexGrow: 2,
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <Button
              sx={{ backgroundColor: "#ffbf00" }}
              component={Link}
              to={`/cyfms/appointment/add/${id}`}
              variant="contained"
            >
              Add New
            </Button>
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Table sx={{ minWidth: 760 }} aria-label="simple table">
              <TableHead>
                <TableRow sx={{ backgroundColor: "#FFBF00", color: "white" }}>
                  <TableCell
                    sx={{ color: "white" }}
                    align="center"
                    size="small"
                  ></TableCell>
                  <TableCell
                    sx={{ color: "white" }}
                    align="center"
                    size="small"
                  >
                    Date
                  </TableCell>
                  <TableCell
                    sx={{ color: "white" }}
                    align="center"
                    size="small"
                  >
                    Subject
                  </TableCell>
                  <TableCell
                    sx={{ color: "white" }}
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
                {data.map((val: any) => (
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
                      <Link
                        to={`/cyfms/appointment/edit/${val.participantId}/${val.participantAppointmentId}`}
                      >
                        Select
                      </Link>
                    </TableCell>
                    <TableCell
                      sx={{ color: "black" }}
                      align="center"
                      size="small"
                    >
                      {val.date}
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
          </Box>
        </Box>
      </Box>
    </CYFMSLayout>
  );
};

export default Appointments;
