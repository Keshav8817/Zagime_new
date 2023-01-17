// import IcLayout from "../../../components/initialContact/ICLayout";
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
import { Link, useParams } from "react-router-dom";
import type { FC } from "react";
import { Data } from "./GoalsObjectiveDataTypes";
import { readAllGoalsAndObjective } from "./service";
import HrLayout from "../../../components/hr/HrLayout";
import { readGoalsAndObjective } from "./service";

// import { searchICContactNotes } from "./service";
// import { Data } from "./ContactNotesDataType";

/**
 * `IC` aka `Initial Contact` module.
 * Sub page: `Contact Notes`.
 */
const GoalsObjective: FC = () => {
  const [value, setValue] = useState("");
  const { id } = useParams();
  const [data, setData] = useState<Data[]>([
    {
      goalsAndObjectivesId: 0,
      date: "",
      status: "",
      supervisor: "",
      planPeriod: "",
      goalsAndObjectives: "",
      guidesAndEnablers: "",
      reviewComments: "",
      reviewedBy: "",
      reviewDate: "",
      staffId: Number(id),
    },
  ]);
  const [state, setState] = useState<Data>({
    goalsAndObjectivesId: 0,
    date: "",
    status: "",
    supervisor: 0,
    planPeriod: "",
    goalsAndObjectives: "",
    guidesAndEnablers: "",
    reviewComments: "",
    reviewedBy: "",
    reviewDate: "",
    staffId: Number(id),
  });
  useEffect(() => {
    readAllGoalsAndObjective(Number(id)).then(({ data }) => {
      setData(data);
    });
    // dispatch(doSearch({ id: state.fileDetailsId, data: "" }));
  }, []);

  // const handleSearchIcon = (e: any) => {
  //   console.group("click");
  //   searchICContactNotes(state.fileDetailsId, value).then(({ data }) => {
  //     setData(data);
  //   });
  // };

  const handleChange = (e: any) => {
    setValue(e.target.value);
  };
  const handleSelected = (id: number) => {
    readGoalsAndObjective(id).then(({ data }) => {});
  };

  return (
    <HrLayout>
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
              to={`../goals&objectives/add/${id}`}
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
                      Date
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
                      Plan Period
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
                          to={`../goals&objectives/edit/${val.staffId}/${val.goalsAndObjectivesId}`}
                          // onClick={() =>
                          //   handleSelected(val.goalsAndObjectivesId)
                          // }
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
                        {val.status}
                      </TableCell>
                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.planPeriod}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Box>
      </Box>
    </HrLayout>
  );
};

export default GoalsObjective;
// import React from "react";

// const ContactNotes = () => {
//   return <div>ContactNotes</div>;
// };

// export default ContactNotes;
