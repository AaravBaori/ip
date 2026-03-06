# Skywalker - User Guide

Skywalker is a **desktop task manager built for the Jedi Order**. Whether you're tracking mission deadlines, planning council events, or just jotting down your training exercises, Skywalker keeps everything organized through a clean, keyboard-first command-line interface.

---

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding a Todo: `todo`](#adding-a-todo--todo)
    * [Adding a Deadline: `deadline`](#adding-a-deadline--deadline)
    * [Adding an Event: `event`](#adding-an-event--event)
    * [Listing all Tasks: `list`](#listing-all-tasks--list)
    * [Marking a Task: `mark`](#marking-a-task--mark)
    * [Unmarking a Task: `unmark`](#unmarking-a-task--unmark)
    * [Deleting a Task: `delete`](#deleting-a-task--delete)
    * [Locating Tasks by Name: `find`](#locating-tasks-by-name--find)
    * [Exiting the Program: `bye`](#exiting-the-program--bye)
    * [Saving the Data](#saving-the-data)
* [FAQ](#faq)
* [Troubleshooting Tips](#troubleshooting-tips)
* [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have **Java 17** installed on your terminal or computer.
2. Download the latest `skywalker.jar` from the releases page.
3. Move it to a folder of your choice; this will become Skywalker's home base.
4. Open a terminal, navigate to that folder, and run: `java -jar skywalker.jar` to boot up the archives.
5. Type any command in the command box and press Enter to execute it.
   Some example commands you can try:
    * `list` : Accesses the Jedi Holocron and lists all missions.
    * `todo master the force` : Adds a new training task to the list.
    * `delete 1` : Deletes the 1st mission in your current list.
    * `bye` : Safely logs out of the system.
6. Refer to the [Features](#features) below for the full command reference.

---

## Features

> **Notes about the command format:**
> * Words in `UPPER_CASE` represent parameters you supply.
    >   e.g. `todo DESCRIPTION` becomes `todo meditate`.
> * Parameters must follow the order shown in each format.
> * Commands that take no parameters (such as `list` and `bye`) must be entered on their own — adding extra input will cause a disturbance in the Force.
    >   e.g. use `bye`, not `bye 123`.
> * The Jedi Code is flexible: Commands are case-insensitive. e.g. `LIST`, `List`, and `list` all work the same way.

### Adding a Todo : `todo`
Adds a standard training exercise or task without any date or time attached to it.

**Format:** `todo DESCRIPTION`

**Example:**
* `todo repair R2-D2`

### Adding a Deadline : `deadline`
Adds a mission that needs to be completed before a specific date or time.

**Format:** `deadline DESCRIPTION /by DATE_TIME`

**Examples:**
* `deadline submit recon report /by Friday`
* `deadline defend Naboo /by 2026-03-28 23:59`

### Adding an Event : `event`
Adds a mission that starts at a specific time and ends at a specific time.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

**Example:**
* `event Jedi Council Meeting /from 09:00 /to 11:30`

### Listing all Tasks : `list`
Shows a complete list of all missions currently in your Jedi Holocron.

**Format:** `list`

### Marking a Task : `mark`
Marks the specified mission in the list as completed.

**Format:** `mark INDEX`
* Marks the task at the specified `INDEX`.
* The index refers to the coordinate number shown in the displayed task list.
* The index **must be a positive integer** (1, 2, 3, …)

**Example:**
* `list` followed by `mark 4` marks the 4th mission in the list as done.

### Unmarking a Task : `unmark`
Marks a previously completed mission as incomplete if it requires further attention.

**Format:** `unmark INDEX`
* The index **must be a positive integer** (1, 2, 3, …)

**Example:**
* `unmark 1` marks the 1st task in the list as not done yet.

### Deleting a Task : `delete`
Deletes the specified mission from the archives permanently.

**Format:** `delete INDEX`
* The index **must be a positive integer** (1, 2, 3, …)

**Example:**
* `delete 2` removes the 2nd task in the task list.

### Locating Tasks by Name : `find`
Searches the archives for all missions whose descriptions contain the specified keyword.

**Format:** `find KEYWORD`
* The search is case-insensitive. e.g. `sith` will match `Sith`.
* Only the mission description is searched.

**Example:**
* `find report` returns tasks like `submit recon report` and `draft council report`.

### Exiting the Program : `bye`
Exits the system and ensures your missions are securely saved.

**Format:** `bye`

### Saving the Data
Skywalker saves your mission list automatically after every change, so no manual saving is needed. Your data is stored locally in `./SkywalkerData.txt`, located in the same directory where you run the program.

> ⚠️ **Note for Jedi Masters:** You may edit `SkywalkerData.txt` directly, but any formatting errors will corrupt the archives and cause Skywalker to start fresh on the next launch. Always keep a backup before manually altering the sacred texts.

---

## FAQ

**Q**: How are my Jedi archives saved?  
**A**: Skywalker automatically saves your missions to the hard drive after every change (add, delete, mark, or unmark). You don't need to save manually. The data is stored in a file named `./SkywalkerData.txt` located in the exact same directory as your application.

**Q**: Can I transfer my missions to another computer or terminal?  
**A**: Yes! Simply copy the `SkywalkerData.txt` file from your current directory and place it in the same directory as the `skywalker.jar` file on your new machine.

**Q**: What happens if I accidentally delete a task? Can I use the Force to bring it back?  
**A**: Unfortunately, once a mission is deleted, it is lost to the dark side and cannot be recovered from within the application. It is wise for a Jedi to make regular backups of the `SkywalkerData.txt` file.

---

## Troubleshooting Tips

1. **"A disturbance in the Force" errors** — If you see this message, it means your command format is incorrect. Double-check that you are using the exact keywords and the required delimiters (`/by` for deadlines, `/from` and `/to` for events).
2. **Missing descriptions or times** — Skywalker will reject commands that lack necessary details. For example, typing just `todo` or `deadline /by tonight` without a description will trigger an error. The archives require complete records.
3. **"Error loading archives" on startup** — This happens if your `./SkywalkerData.txt` file is corrupted or formatted incorrectly via manual editing. If this occurs, Skywalker may fail to load your past tasks. Always back up the file before attempting to manually edit the sacred texts.
4. **Unexpected date formats** — Skywalker currently stores dates and times *exactly* as you type them as text (e.g., "tonight", "Next Friday", "2026-03-28"). If a date looks wrong in your list, delete the task and re-add it with your preferred phrasing.
---

## Command Summary

| Action | Format | Example |
|--------|--------|---------|
| **Todo** | `todo DESCRIPTION` | `todo clean lightsaber` |
| **Deadline** | `deadline DESCRIPTION /by DATE_TIME` | `deadline secure blueprints /by tonight` |
| **Event** | `event DESCRIPTION /from START_TIME /to END_TIME` | `event trench run /from 14:00 /to 15:00` |
| **List** | `list` | `list` |
| **Mark** | `mark INDEX` | `mark 4` |
| **Unmark** | `unmark INDEX` | `unmark 3` |
| **Delete** | `delete INDEX` | `delete 2` |
| **Find** | `find KEYWORD` | `find blueprints` |
| **Bye** | `bye` | `bye` |

---
*Developed by Aarav Baori for CS2113.*