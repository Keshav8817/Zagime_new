import SearchIcon from "@mui/icons-material/Search";
import {
  Box,
  Button,
  OutlinedInput,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { grey } from "@mui/material/colors";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import type { FC } from "react";
// import { searchICContactNotes } from "./service";

// import { NeedAssessmentAdd } from "./Add";
import FhLayout from "../../../components/fh/FhLayout";
import { Data } from "./ProgressReportsDataType";
import { readAllProgressReport } from "./ProgressreportsService";

/**
 * `IC` aka `Initial Contact` module.
 * Sub page: `Contact Notes`.
 */
const ProgressReports: FC = () => {
  const [value, setValue] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
  const [data, setData] = useState<Data[]>([
    {
      fhProgressReportId: 0,
      dateOfAssessment: "",
      completedBy: "",
      areas: "",
      sessionsAttended: "",
      sessions: "",
      progress: "",
      fhFileDetailsId: 0,
    },
  ]);
  const [state, setState] = useState<Data>({
    fhProgressReportId: 0,
    dateOfAssessment: "",
    completedBy: "",
    areas: "",
    sessionsAttended: "",
    sessions: "",
    progress: "",
    fhFileDetailsId: Number(id) | 0,
  });
  useEffect(() => {
    readAllProgressReport(state.fhFileDetailsId).then(({ data }) => {
      setData(data);
    });
  }, []);

  const handleChange = (e: any) => {
    setValue(e.target.value);
  };

  return (
    <FhLayout>
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
              to={`../progressReports/add/${id}`}
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
                    <TableCell align="center" size="small"></TableCell>
                    <TableCell align="center" size="small">
                      Date of Assessment
                    </TableCell>
                    <TableCell align="center" size="small">
                      Completed By
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
                          to={`../progressReports/edit/${val.fhFileDetailsId}/${val.fhProgressReportId}`}
                        >
                          Select
                        </Link>
                      </TableCell>

                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.dateOfAssessment}
                      </TableCell>
                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.completedBy}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Box>
      </Box>
    </FhLayout>
  );
};

export default ProgressReports;
