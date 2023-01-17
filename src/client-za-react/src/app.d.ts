/**
 * Application specific event handler.
 * Allows to pass it extra extra arguments.
 */
type AppEventHandler<E extends SyntheticEvent<any>> = {
  bivarianceHack(event: E, ...args: any): unknown;
}["bivarianceHack"];

/** Application specific form event handler. */
type AppFormEventHandler<T = Element> = AppEventHandler<FormEvent<T>>;

/** Application specific mouse event handler. */
type AppMouseEventHandler<T = Element> = AppEventHandler<MouseEvent<T>>;

/** Application specific generic record props. */
type AppRecordProps<R> = {
  /** Holds data within a record. */
  record: R;
  /** Uniquely identifies a record. */
  number: number;
};

/** Application specific generic record list props. */
type AppRecordListProps<R> = {
  /** List of record `R`. */
  list: R[];
};
