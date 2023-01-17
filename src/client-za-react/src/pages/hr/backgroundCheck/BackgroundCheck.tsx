import CgLayout from "../../../components/hr/HrLayout";

import {
  Box,
  Button,
  OutlinedInput,
  Table,
  TableContainer,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
} from "@mui/material";
import { grey } from "@mui/material/colors";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import type { FC } from "react";
import { Data } from "./BackgroundCheckDatatypes";
import { readAllBackgroundCheck } from "./service";

/**
 * `CG` aka `Caregivers` module.
 * Sub page: `Reminders`.
 */
const BackgroundCheck: FC = () => {
  const [value, setValue] = useState("");
  const { id } = useParams();
  const [data, setData] = useState<Data[]>([
    {
      backgrounCheckId: 0,
      typeOfCheck: "",
      status: "",
      dateRequested: "",
      dateCompleted: "",
      requestedBy: "",
      notes: "",
      staffId: Number(id),
    },
  ]);
  const [state, setState] = useState<Data>({
    backgrounCheckId: 0,
    typeOfCheck: "",
    status: "",
    dateRequested: "",
    dateCompleted: "",
    requestedBy: "",
    notes: "",
    staffId: Number(id),
  });

  useEffect(() => {
    readAllBackgroundCheck(Number(id)).then(({ data }) => {
      setData(data);
    });
    // dispatch(doSearch({ id: state.fileDetailsId, data: "" }));
  }, []);

  // const handleSearchIcon = (e: any) => {
  //   dispatch(
  //     doSearch({
  //       id: state1.getData.id ? state1.getData.id : state1.data.id,
  //       data: value,
  //     })
  //   );
  // };

  const handleChange = (e: any) => {
    setValue(e.target.value);
  };

  return (
    <CgLayout>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
      >
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
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
              to={`../backgroundCheck/add/${Number(id)}`}
              variant="contained"
            >
              Add New
            </Button>
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <TableContainer>
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
                      Type of Check
                    </TableCell>
                    <TableCell
                      sx={{ color: "white" }}
                      align="center"
                      size="small"
                    >
                      Status
                    </TableCell>
                    <TableCell
                      sx={{ color: "white" }}
                      align="center"
                      size="small"
                    >
                      Date Completed
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
                  {data.map((val: Data) => (
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
                          to={`../backgroundCheck/edit/${val.staffId}/${val.backgrounCheckId}`}
                        >
                          Select
                        </Link>
                      </TableCell>
                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.typeOfCheck}
                      </TableCell>
                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.status}
                      </TableCell>
                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.dateCompleted}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Box>
      </Box>
    </CgLayout>
  );
};

export default BackgroundCheck;
