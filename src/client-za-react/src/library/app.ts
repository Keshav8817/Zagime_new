import type { KeyboardEventHandler } from "react";

/**
 * Prevent form submission upon hitting "Enter" key
 * with the exception of "Submit" button.
 */
export const onKeyDown: KeyboardEventHandler<HTMLFormElement> = (e) => {
  const n = e.target as Element; // where, n = node
  if (e.key === "Enter" && n.tagName.toLowerCase() === "input") {
    e.preventDefault();
    n.closest("form")
      ?.querySelectorAll("input,button,a")
      .forEach((node, index, nodes) => {
        /* Skip the "Submit" button node
         * as it is the last one. */
        if (index !== nodes.length - 1) return;
        /* Focus next node. */
        if (node.isEqualNode(n)) {
          (nodes[index + 1] as HTMLElement).focus();
        }
      });
  }
};
