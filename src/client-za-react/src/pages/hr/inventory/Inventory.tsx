import {
  CYFSWMSAddButton,
  CYFSWMSSaveButton,
} from "../../../components/CYFSWMSButtons";
import HrLayout from "../../../components/hr/HrLayout";
import RecordList1 from "../../../components/hr/inventory/RecordList";
import { onKeyDown } from "../../../library/app";
// import { useAppDispatch, useAppSelector } from "../../../library/hooks";
// import { handleEffect, handleAddMore, handleSubmit } from "./inventory_";
import { Box } from "@mui/material";
import { useNavigate, useParams } from "react-router-dom";
import type { FC } from "react";
import React, { useEffect, useRef, useState } from "react";
import { getInventory, PostInventory } from "./inventoryService";
import { inventory } from "./inventoryDatatypes";
import { IdleTimerProvider } from "react-idle-timer";
import EditModeButton from "../../../components/hr/Button/EditModeButton";

/**
 * `HR` aka `Human Resources` module.
 * Sub page: `Inventory`.
 */
const Inventory: FC = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [disabled, setDisabled] = useState(false);
  const [status, setStatus] = useState({});
  const formRef = useRef<HTMLFormElement>(null);
  const [data, setData] = useState([
    {
      staffId: Number(id),
      staffInventoryId: 0,
      item: "",
      serialNo: 0,
      from: "",
      to: "",
      inUse: false,
      notes: "",
    } as inventory,
  ]);
  const addFields = () => {
    let object: any = {
      staffId: Number(id),
      staffInventoryId: 0,
      item: "",
      serialNo: 0,
      from: "",
      to: "",
      inUse: false,
      notes: "",
    };
    setData([...data, object]);
  };
  useEffect(() => {
    // getGendersCodetable().then((data) => {
    //   setStatus(data.data.valuesMap);
    // });
    getInventory(Number(id)).then(({ data }) => {
      if (data.length > 0) {
        setData(data);
        setDisabled(true);
      }
    });
  }, []);
  const submithandler = (event: any) => {
    event.preventDefault();

    const formData: any = [];
    for (let index = 0; index < data.length; ++index) {
      formData[index] = {
        staffId: data[index].staffId,
        staffInventoryId: data[index].staffInventoryId,
        item: data[index].item,
        serialNo: data[index].serialNo,
        from: data[index].from,
        to: data[index].to,
        inUse: data[index].inUse,
        notes: data[index].notes,
      };
    }
    PostInventory(formData).then(() => {
      navigate(`../Staff/${id}`);
    });
  };
  return (
    <HrLayout>
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
        onSubmit={submithandler}
        ref={formRef}
        onKeyDown={onKeyDown}
      >
        <>
          <EditModeButton
            id={data[0].staffInventoryId}
            path={`/hr/Staff/${id}`}
            fileDetailsPage={true}
            module="inventory"
            setDisabled={setDisabled}
            disabled={disabled}
          />
        </>
        <Box sx={{ display: "flex", flexDirection: "column", gap: "1rem 0" }}>
          <RecordList1
            data={data}
            setData={setData}
            status={status}
            disabled={disabled}
          />
        </Box>
        <Box>
          <CYFSWMSAddButton disabled={disabled} onClick={addFields} />
        </Box>
        <Box sx={{ display: "flex", gap: "0 1rem", justifyContent: "right" }}>
          <CYFSWMSSaveButton disabled={disabled} />
        </Box>
      </Box>
    </HrLayout>
  );
};

export default Inventory;
