let liste = []; //Variabelen må være global slik at den kan endres i begge funksjonene

function kjopBillett() {
  const film = $("#film-liste").val(); //Henter verdier
  const antall = $("#antall-felt").val();
  const fornavn = $("#fornavn-felt").val();
  const etternavn = $("#etternavn-felt").val();
  const telefonnr = $("#telefonnr-felt").val();
  const epost = $("#epost-felt").val();

  const feilAntall = $("#tom-antall");
  const feilFornavn = $("#tom-fornavn");
  const feilEtternavn = $("#tom-etternavn");
  const feilTelefonnr = $("#tom-telefonnr");
  const feilEpost = $("#tom-epost");

  if (!validerNavn() || !validerEpost() || !validerTelefonnr()) {
    // Validate the name first
    return;
  }
  let kunde = {
    //Opretter objektet

    film: film,
    antall: antall,
    fornavn: fornavn,
    etternavn: etternavn,
    telefonnummer: telefonnr,
    epost: epost,
  };

  console.log(kunde);
  $.post("/lagre", kunde, function () {
    hentAlle();
  });

  function hentAlle() {
    $.get("/hent", function (data) {
      formaterBilett(data);
    });
  }

  if (kunde.antall === "") {
    feilAntall.html("Antall mangler");
  } //Sjekker om det skrives noe i feltet,
  else feilAntall.html(""); //dersom feltet er tomt sendes feilmeldingen, og når feltet blir fylt fjernes den

  if (kunde.fornavn === "") {
    feilFornavn.html("Fornavn mangler");
  } else feilFornavn.html("");

  if (kunde.etternavn === "") {
    feilEtternavn.html("Etternavn mangler");
  } else feilEtternavn.html("");

  if (kunde.telefonnummer === "") {
    feilTelefonnr.html("Telefonnummer mangler");
  } else feilTelefonnr.html("");

  if (kunde.epost === "") {
    feilEpost.html("Epost mangler");
  } else feilEpost.html("");

  if (
      //Her sjekkes det om alle feltene har fått en verdi,
      kunde.antall !== "" && // og hvis de har fått en verdi så dyttes objektet inn i lista.
      kunde.fornavn !== "" && // I tillegg kjøres utMelding funksjonen som presenterer objektet på en fin måte
      kunde.etternavn !== "" && // Til slutt tømmes feltene slik at "neste" kunde kan fylle de inn igjen
      kunde.telefonnr !== "" &&
      kunde.epost !== ""
  ) {
    liste.push(kunde);
    formaterBilett();

    $("#film-liste").val("");
    $("#antall-felt").val("");
    $("#fornavn-felt").val("");
    $("#etternavn-felt").val("");
    $("#telefonnr-felt").val("");
    $("#epost-felt").val("");
  }
}

function formaterBilett() {
  let ut =
      "<tr>" +
      "<th>Film</th>" + // Setter opp visningen av bestillinger slik vist i opggaven
      "<th>Antall</th>" +
      "<th>Fornavn</th>" +
      "<th>Etternavn</th>" +
      "<th>Telefonnr</th>" +
      "<th>Epost</th>" +
      "</tr>";
  for (let bestilling of liste) {
    ut += "<tr>";

    ut +=
        "<td>" +
        bestilling.film +
        "</td>" + // Henter ut alle bestillingene fra lista
        "<td>" +
        bestilling.antall +
        "</td>" +
        "<td>" +
        bestilling.fornavn +
        "</td>" +
        "<td>" +
        bestilling.etternavn +
        "</td>" +
        "<td>" +
        bestilling.telefonnummer +
        "</td>" +
        "<td>" +
        bestilling.epost +
        "</td>" +
        "<br>";

    ut += "</tr>";
  }

  document.getElementById("utskrift").innerHTML = ut; // Til slutt skriver jeg ut "ut" på siden
}

function slettBiletter() {
  const slett = confirm("Vil du slette?");
  if (slett) {
    $.get("/slett", function (data) {
      formaterBilett(data);
      $("#utskrift").html("");
    });
  }
  liste = []; // Her setter jeg listen til å være tom
  formaterBilett(); // Dette tømmer tabellen som viser bestillingene
}

function validerNavn() {
  const regexp = /^[a-zA-Z]{2,30}$/; // This regex matches exactly 4 uppercase letters
  const fNavn = $("#fornavn-felt").val();
  const feilFornavn = $("#tom-fornavn");

  if (!regexp.test(fNavn)) {
    feilFornavn.html("Fornavnet må inneholde 2 til 30 bokstaver og bare bokstaver");
    return false;
  } else {
    feilFornavn.html("");
    return true;
  }
}

function validerTelefonnr() {
  const regexp = /^[0-9]{8}$/; // This regex matches exactly 8 digits
  const telefonnr = $("#telefonnr-felt").val();
  const feilTelefonnr = $("#tom-telefonnr");

  if (!regexp.test(telefonnr)) {
    feilTelefonnr.html("Telefonnummeret må være 8 siffer");
    return false;
  } else {
    feilTelefonnr.html("");
    return true;
  }
}

function validerEpost() {
  const regexp = /^[^@]+@[^@]+\.[^@]+$/; // Simple regex to check for a valid email format
  const epost = $("#epost-felt").val();
  const feilEpost = $("#tom-epost");

  if (!regexp.test(epost)) {
    feilEpost.html("Epost-adressen er ikke gyldig");
    return false;
  } else {
    feilEpost.html("");
    return true;
  }
}


