import axiosInstance from "../../../library/axiosInstance";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { Box, Button, IconButton, Modal, Menu, MenuItem } from "@mui/material";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import type { Search } from "../../../pages/cyfms/search/searchDatatypes";
import type { Dispatch, FC, SetStateAction } from "react";

const EditIcon: FC<{
  record: Search;
  index: number;
  list: Search[];
  setData: Dispatch<SetStateAction<Search[]>>;
}> = (props) => {
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [openModel, setOpenModel] = React.useState(false);
  const openDropDown = Boolean(anchorEl);

  return (
    <div>
      <IconButton
        aria-label="more"
        id="long-button"
        aria-controls={openDropDown ? "long-menu" : undefined}
        aria-expanded={openDropDown ? "true" : undefined}
        aria-haspopup="true"
        onClick={(event) => setAnchorEl(event.currentTarget)}
      >
        <MoreHorizIcon />
      </IconButton>
      <Menu
        id="long-menu"
        MenuListProps={{
          "aria-labelledby": "long-button",
        }}
        anchorEl={anchorEl}
        open={openDropDown}
        onClose={(event, reason) => setAnchorEl(null)}
        PaperProps={{
          style: {
            maxHeight: 48 * 4.5,
            width: "20ch",
          },
        }}
      >
        <MenuItem
          component={Link}
          to="#"
          onClick={(event) => {
            event.preventDefault();
            // doGetRegister(props.value).then((response) => {
            navigate("/cyfms/register");
            // });
          }}
        >
          Edit
        </MenuItem>
        <MenuItem
          to="#"
          component={Link}
          onClick={(event) => {
            event.preventDefault();
            setOpenModel(true);
            setAnchorEl(null);
          }}
        >
          Delete
        </MenuItem>
      </Menu>
      <Modal
        open={openModel}
        onClose={(event, reason) => setOpenModel(false)}
        aria-labelledby="parent-modal-title"
        aria-describedby="parent-modal-description"
      >
        <Box
          sx={{
            position: "absolute" as "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "background.paper",
            boxShadow: 24,
            pt: 2,
            px: 4,
            pb: 3,
            paddingLeft: "5%",
          }}
        >
          <p id="parent-modal-description">Are you sure you want to delete?</p>
          <Box paddingLeft={7}>
            <Button
              onClick={(e) => {
                e.preventDefault();
                axiosInstance
                  .delete(
                    `participantservice/removeParticipant/${props.record.referenceId}`,
                    {
                      headers: {
                        authorization: `Bearer ${localStorage.getItem(
                          "jwtToken"
                        )}`,
                      },
                    }
                  )
                  .then((response) => {
                    setOpenModel(false);
                    console.log(props.list);
                    props.list.splice(props.index, 1);
                    console.log(props.list);
                    props.setData(props.list);
                  });
              }}
            >
              Yes
            </Button>
            <Button onClick={() => setOpenModel(false)}>No</Button>
          </Box>
        </Box>
      </Modal>
    </div>
  );
};

export default EditIcon;
