import {
  Box,
  Button,
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
import CpaLayout from "../../../components/cpa/CPALayout";
import { Data } from "./ParticipantsDatatypes";
import { readParticiapnts, searchCPAParticipant } from "./Service";

/**
 * `CPA` aka `Cultural Program And Activity` module.
 * Sub page: `Participants`.
 * @returns `ReactElement`
 */
const Participants: FC = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [data, setData] = useState<Data[]>([
    {
      participantCulturalProId: 0,
      culturalProgramId: Number(id),
      participant: null,
      role: "",
      notes: "",
    },
  ]);
  const [state, setState] = useState<Data>({
    participantCulturalProId: 0,
    culturalProgramId: Number(id),
    participant: null,
    role: "",
    notes: "",
  });
  const [addNew, setAddNew] = useState(false);
  const [disabled, setDisabled] = useState(false);

  useEffect(() => {
    searchCPAParticipant(Number(id), "").then(({ data }) => {
      setData(data);
    });
  }, []);

  // const handleAddNew = () => {};

  const handleSelected = (id: number) => {
    readParticiapnts(id).then(({ data }) => {});
  };

  return (
    <CpaLayout>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
      >
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
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
              to={`../participants/add/${id}`}
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
                <TableHead sx={{ backgroundColor: "#FFBF00", color: "white" }}>
                  <TableRow>
                    <TableCell align="center" size="small"></TableCell>
                    <TableCell align="center" size="small">
                      Participant
                    </TableCell>
                    <TableCell align="center" size="small">
                      Role
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
                    <TableRow>
                      <TableCell>
                        <Link
                          to={`../participants/edit/${val.culturalProgramId}/${val.participantCulturalProId}`}
                          onClick={() =>
                            handleSelected(val.participantCulturalProId)
                          }
                        >
                          Select
                        </Link>
                      </TableCell>
                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.participant}
                      </TableCell>
                      <TableCell
                        sx={{ color: "black" }}
                        align="center"
                        size="small"
                      >
                        {val.role}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Box>
      </Box>
    </CpaLayout>
  );
};

export default Participants;
