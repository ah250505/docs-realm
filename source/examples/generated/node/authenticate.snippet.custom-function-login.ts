// Create a custom function credential
const credentials = Realm.Credentials.function({ username: "ilovemongodb" });
try {
  const user = await app.logIn(credentials);
  console.log("Successfully logged in!", user.id);
  return user;
} catch (err) {
  if (err instanceof Error) {
    console.error("Failed to log in", err.message);
  }
}
