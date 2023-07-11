# How to start?

<b>Intellij IDEA</b> is recommended.
<ul>
<li>Clone the repository and install the dependencies using Maven.</li>
<li>Make sure PostgresSQL is running.</li>
<li>Take a copy of <code>src/main/resources/application.properties.example</code> file and rename it to <code>src/main/resources/application.properties</code>.</li>
<li>Set the necessary configurations in <code>application.properties</code> file.</li>
    <ul>
        <li>Set the DB username, password and db name.</li>
        <li>The database should be already created.</li>
    </ul>
<li>Start the application. The Database tables will be automatically migrated.</li>
</ul>