[![Build Status](https://travis-ci.org/levelrin/JavaSingleValue.svg?branch=master)](https://travis-ci.org/levelrin/JavaSingleValue)
[![Test Coverage](https://img.shields.io/codecov/c/github/levelrin/JavaSingleValue.svg)](https://codecov.io/github/levelrin/JavaSingleValue?branch=master)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/levelrin/JavaSingleValue/blob/master/LICENSE)

# JavaSingleValue

Java objects that represent singe value. 
Using normal variable to store data and manipulating them is no fun. 
Why not let objects maintain the data for you? 
Check out the examples below.

### Caching and Lazy-loading

Let's say we have `User` object, and we can ask him to give us his description. 
He may need to put some effort to compute his description. 
We want him to do computation only when we ask his description (lazy-loading).
Lastly, we wnt him to cache his description instead of computing every time we ask him. 
Most likely, this is how we may implement the User object in tedious way:
```java
public class User {

    private String content;
    private boolean cached;

    /**
     * The description will be computed.
     * We execute the logic here, not in the constructor (lazy-loading).
     * We also cache the computed value.
     * @return User description.
     */
    public String description() {
        if (!this.cached) {
            // Imagine we do some computation.
            // For example, fetching data from server, or getting data from database.
            // After the computation, we assign the value to the instance variable.
            this.content = "Some description of the user.";
            this.cached = true;
        }
        return this.content;
    }

}
```

And this is how we may implement the User object with `CachedValue` object, which is from this library :)
```java
public class User {

    /**
     * We give the computation logic to this object.
     * It will execute the logic only once and cache the value for us.
     */
    private final CachedValue<String> description = new CachedValue<>(() -> {
        // Imagine we do some computation.
        // For example, fetching data from server, or getting data from database.
        // After the computation, we assign the value to the instance variable.
        return "Some description of the user.";
    });

    /**
     * The description will be computed.
     * We execute the logic here, not in the constructor (lazy-loading).
     * We also cache the computed value.
     * @return User description.
     */
    public String description() {
        return this.description.get();
    }

}
```
