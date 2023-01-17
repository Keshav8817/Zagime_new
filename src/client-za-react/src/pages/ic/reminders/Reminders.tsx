import IcLayout from "../../../components/initialContact/ICLayout";
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
import { Data } from "./ReminderDataTypes";
import { searchICReminder } from "./service";
import { idText } from "typescript";
/**
 * `IC` aka `Initial Contact` module.
 * Sub page: `Reminders`.
 */
const Reminders: FC = () => {
  const { id } = useParams();
  const [addNew, setAddNew] = useState(false);
  const [disabled, setDisabled] = useState(false);
  const [value, setValue] = useState("");
  const [data, setData] = useState<Data[]>([
    {
      icReminderId: 0,
      fileDetailsId: Number(id),
      fileNumber: 0,
      reminderDto: {
        reminderId: 0,
        assignedTo: "",
        regarding: "",
        subject: "",
        status: "",
        reminderDate: "",
        endDate: "",
        description: "",
        frequency: "",
      },
    },
  ]);
  const [state, setState] = useState<Data>({
    icReminderId: 0,
    fileDetailsId: Number(id),
    fileNumber: 0,
    reminderDto: {
      reminderId: 0,
      assignedTo: "",
      regarding: "",
      subject: "",
      status: "",
      reminderDate: "",
      endDate: "",
      description: "",
      frequency: "",
    },
  });

  useEffect(() => {
    searchICReminder(Number(id), "").then(({ data }) => {
      setData(data);
    });
  }, []);

  const handleSearchIcon = (e: any) => {
    searchICReminder(Number(id), value).then(({ data }) => {
      setData(data);
    });
  };

  const handleChange = (e: any) => {
    setValue(e.target.value);
  };
  return (
    <IcLayout>
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
              to={`../reminder/add/${id}`}
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
                <TableRow sx={{ backgroundColor: "#ffbf00", color: "white" }}>
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
                      <Link to={`../reminder/edit/${id}/${val.icReminderId}`}>
                        Select
                      </Link>
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
          </Box>
        </Box>
      </Box>
    </IcLayout>
  );
};

export default Reminders;
