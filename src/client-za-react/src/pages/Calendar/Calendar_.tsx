import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import "../../index.css";
import { Box, Typography } from "@mui/material";

import AuthLayout from "../../components/auth/layout/AuthLayout";
import { useCallback, useEffect, useState } from "react";
import { useMediaQuery, useTheme } from "@mui/material";

import AppointmentEvent from "./AppointmentEvent";

import ReminderEvent from "./ReminderEvent";
import { getAllCaledarData } from "./service";

const localizer = momentLocalizer(moment);

const initialArtists = [{ id: 0, title: "Neeraj", start: "", end: "" }];
const Calendar_ = (props: any) => {
  const [open, setOpen] = useState(false);
  const [title, setTitle] = useState(["No Event"]);
  const [newEvent, setNewEvent] = useState(initialArtists);

  const [start, setStart] = useState(
    moment(new Date()).format(" ddd, DD MMM yyyy ")
  );

  const [end, setEnd] = useState(
    moment(new Date()).format(" ddd, DD MMM yyyy ")
  );
  const [selectedDate, setSelectedDate] = useState("");
  const theme = useTheme();

  const isMatch = useMediaQuery(theme.breakpoints.down("sm"));

  useEffect(() => {
    getAllCaledarData().then(({ data }) => {
      const events = data.map((key: any) => ({
        id: key.appointmentId ? key.appointmentId : key.reminderId,
        title: key.subject,
        start: key.date
          ? moment(key.date).toDate()
          : moment(key.reminderDate).toDate(),
        end: key.date
          ? moment(key.date).toDate()
          : moment(key.reminderDate).toDate(),
      }));
      setNewEvent(events);
    });
  }, [open]);
  const showEvent = (e: any) => {
    setTitle(e.title);
    setStart(moment(new Date(e.start)).format(" ddd, DD MMM yyyy "));
    setEnd(moment(new Date(e.end)).format(" ddd, DD MMM yyyy "));
  };

  const dayPropGetter = useCallback(
    (date: Date) => {
      return {
        ...(moment(date).format("DD/MM/yyyy") === selectedDate && {
          style: { backgroundColor: "#8ac9c669" },
        }),
      };
    },
    [selectedDate]
  );

  const selectedSlot = (slotInfo: any) => {
    setSelectedDate(moment(slotInfo.start).format("yyyy-MM-DD"));
  };

  const handleEvent = (e: any) => {
    setSelectedDate(moment(e.start).format("yyyy-MM-DD"));
  };

  return (
    <>
      <AuthLayout>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: "1rem 0",
            padding: "1%",
          }}
        >
          {isMatch ? (
            <>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 0rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <Calendar
                    selectable
                    className="rbc-button-link rbc-show-more rbc-month-view "
                    events={newEvent}
                    startAccessor="start"
                    endAccessor="end"
                    onSelectEvent={handleEvent}
                    defaultDate={moment(new Date()).toDate()}
                    min={new Date(0, 0, 0, 9, 0, 0)}
                    localizer={localizer}
                    onDoubleClickEvent={showEvent}
                    eventPropGetter={(isSelected: any) => ({
                      isSelected,
                      style: { backgroundColor: "orange", color: "white" },
                    })}
                    dayPropGetter={dayPropGetter}
                    style={{
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                      height: "280px",
                      width: "370px",
                    }}
                    onSelectSlot={selectedSlot}
                    views={{ month: true }}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <Typography sx={{ fontWeight: 1000 }}>
                    Appointments
                  </Typography>
                  <Box
                    sx={{
                      height: 180,
                      flexBasis: 0,
                      flexGrow: 1,
                      overflow: "hidden",
                      overflowY: "scroll",
                    }}
                  >
                    <AppointmentEvent date={selectedDate} />
                  </Box>
                  <Typography sx={{ fontWeight: 1000 }}>Reminders</Typography>
                  <Box
                    sx={{
                      height: 180,
                      flexBasis: 0,
                      flexGrow: 1,
                      overflow: "hidden",
                      overflowY: "scroll",
                    }}
                  >
                    <ReminderEvent date={selectedDate} />
                  </Box>
                </Box>
              </Box>
            </>
          ) : (
            <>
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 0rem" }}>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <Calendar
                    selectable
                    className="rbc-button-link rbc-show-more rbc-month-view "
                    events={newEvent}
                    startAccessor="start"
                    endAccessor="end"
                    onSelectEvent={handleEvent}
                    defaultDate={moment(new Date()).toDate()}
                    min={new Date(0, 0, 0, 9, 0, 0)}
                    localizer={localizer}
                    onDoubleClickEvent={showEvent}
                    eventPropGetter={(isSelected: any) => ({
                      isSelected,
                      style: { backgroundColor: "orange", color: "white" },
                    })}
                    dayPropGetter={dayPropGetter}
                    style={{
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                      height: "400px",
                      width: "600px",
                    }}
                    onSelectSlot={selectedSlot}
                    views={{ month: true }}
                  />
                </Box>
                <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
                  <Typography sx={{ fontWeight: 1000 }}>
                    Appointments
                  </Typography>
                  <Box
                    sx={{
                      height: 180,
                      flexBasis: 0,
                      flexGrow: 1,
                      overflow: "hidden",
                      overflowY: "scroll",
                    }}
                  >
                    <AppointmentEvent date={selectedDate} />
                  </Box>
                  <Typography sx={{ fontWeight: 1000 }}>Reminders</Typography>
                  <Box
                    sx={{
                      height: 180,
                      flexBasis: 0,
                      flexGrow: 1,
                      overflow: "hidden",
                      overflowY: "scroll",
                    }}
                  >
                    <ReminderEvent date={selectedDate} />
                  </Box>
                </Box>
              </Box>
            </>
          )}
        </Box>
      </AuthLayout>
    </>
  );
};

export default Calendar_;
