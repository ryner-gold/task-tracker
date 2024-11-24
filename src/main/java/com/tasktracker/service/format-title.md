## Format Title Approach

I was curious how I'd be able to format the title without using the String class.
Since String is immutable, I didn't understand how to get the result I wanted.
<br/><br/>
My problem was for `String foo`, I didn't understand I had to concatenate different
operations together to get the overall output:

`foo = foo.strip() + foo.toUpperCase() // Example`

What I ended up doing was going on a fun bizzare route with `String Builder`. It was
quite satisfying figuring out how to format it, I can proudly say I didn't give in and
used Claude to help me out.

```java
 public static StringBuilder formatTitle(StringBuilder title) {
    // Remove all leading white space
    for (int i = 0; i < title.length(); i++) {
        if (!Character.isWhitespace(title.charAt(i))) {
            title.delete(0,
                         i);
            break;
        }
    }

    title.setCharAt(0,
                    Character.toUpperCase(title.charAt(0)));

    // Remove all ending white space
    for (int c = title.length() - 1; c > 0; c--) {
        if (!Character.isWhitespace(title.charAt(c))) {
            title.delete(c + 1,
                         title.length());
            break;
        }
    }


    return title;
}
```
