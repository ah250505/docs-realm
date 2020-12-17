val config = SyncConfiguration.Builder(app.currentUser(), PARTITION)
    .allowQueriesOnUiThread(true)
    .allowWritesOnUiThread(true)
    .build()

Realm.getInstanceAsync(config, object : Realm.Callback() {
    override fun onSuccess(realm: Realm) {
        Log.v(
            "EXAMPLE",
            "Successfully opened a realm with reads and writes allowed on the UI thread."
        )

        realm.executeTransaction { transactionRealm -> ;
            val newDog = Dog("henry")
            val newPerson = Person("dwayne")
            newPerson.dog = newDog
            transactionRealm.insert(newPerson)
        }

        realm.executeTransaction { transactionRealm ->
            val owner = transactionRealm.where<Person>().equalTo("dog.name", "henry").findFirst()
            val dog = owner?.dog
            Log.v("EXAMPLE", "Queried for people with dogs named 'henry'. Found $owner, owner of $dog")
            Assert.assertEquals(dog?.name, "henry")
            Assert.assertEquals(owner?.name, "dwayne")
        }
        expectation.fulfill()
        realm.close()
    }
})