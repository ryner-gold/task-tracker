# Why I Chose StringBuilder for My Task Manager

Hey! I'm building a simple task manager in Java and had to figure out how to handle text processing. Here's my thought
process:

## What My Project Needs

- It's just a simple task manager (adding/editing/deleting tasks)
- I need to clean up user input (remove spaces, capitalize stuff)
- It's just me using it - no fancy multi-user stuff!
- I want to be able to mess around and add features easily

## The Options

First I read this neat article on [Baeldung](https://www.baeldung.com/java-mutable-vs-immutable-objects) about mutable
vs immutable objects. Basically:

- Mutable = you can change it (like editing a notebook)
- Immutable = can't change it (like carving in stone)

In Java, we've got three main ways to handle text:

1. `String` - immutable, creates a new string every time you change something
2. `StringBuilder` - mutable, good for single-user stuff
3. `StringBuffer` - like StringBuilder but for when multiple people need to edit at once

## Why StringBuilder Won

I went with StringBuilder because:

- It's just me using the app (no need for fancy thread safety)
- I'm doing multiple edits to the text (spaces, caps, etc.)
- It's faster than creating new Strings all the time
- StringBuffer would be overkill (it's meant for multi-user apps)

That's it! Nothing too fancy - just picking the right tool for a simple job! 😊

-Ryner

Credits: ClaudeBot@Anthropic 🤖