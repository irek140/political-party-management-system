import { Component, OnInit, Input } from '@angular/core';
import { SurveysResultsListComponent } from '../surveys-results-list/surveys-results-list.component';
import { SurveyService } from 'src/app/services/survey.service';
import { Survey6 } from 'src/app/model/surveys/Survey6';
import { UserService } from 'src/app/services/user.service';
import * as jsPDF from 'jspdf';
import 'jspdf-autotable';

@Component({
  selector: 'app-surveys6-results-details',
  templateUrl: './surveys6-results-details.component.html',
  styleUrls: ['./surveys6-results-details.component.css']
})
export class Surveys6ResultsDetailsComponent implements OnInit {

  @Input()
  survey6: Survey6;

  id: number;
  hidden: boolean;

  view: any[] = [700, 400];
  // options
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;
  legendPosition: string = "below";

  colorScheme = {
    domain: ["#5AA454", "#A10A28", "#C7B42C", "#AAAAAA"]
  };

  surveyObject: any[];

  numberOfVotes: number;

  tooday: string;

  constructor(private surveyService: SurveyService, private userService: UserService, private surveysList: SurveysResultsListComponent) { }

  ngOnInit() {
    console.log(this.survey6.id + ' ' + this.survey6.question);
    this.getMe();
    this.hidden = false;
    this.surveyObject = [
      {
        "name": this.survey6.answer1,
        "value": this.survey6.value1
      }, {
        "name": this.survey6.answer2,
        "value": this.survey6.value2
      }, {
        "name": this.survey6.answer3,
        "value": this.survey6.value3
      }, {
        "name": this.survey6.answer4,
        "value": this.survey6.value4
      }, {
        "name": this.survey6.answer5,
        "value": this.survey6.value5
      }, {
        "name": this.survey6.answer6,
        "value": this.survey6.value6
      }
    ];
    this.numberOfVotes = this.survey6.value1 + this.survey6.value2 + this.survey6.value3 + this.survey6.value4 + this.survey6.value5 + this.survey6.value6;
  }

  print() {
    let doc = new jsPDF();
    doc.setFont('helvetica');
    doc.setFontType('bold');
    doc.setFontSize(25);
    doc.text(95, 40, 'Raport');
    //doc.addFileToVFS("abhafont.ttf");
    //doc.addFont('abhafont', "Abha", "normal", "Identity-H");
    doc.setFontType('normal');
    doc.setFontSize(13);
    doc.text(20, 60, 'Autor: ' + this.survey6.owner.name + ' ' + this.survey6.owner.surname);
    doc.text(20, 70, 'Glosowanie od: ' + this.survey6.created);
    doc.text(20, 80, 'Glosowanie do: ' + this.survey6.enddate);
    doc.text(20, 90, 'Data utworzenia raportu: ' + this.tooday);
    doc.text(20, 110, 'Tresc pytania: ');
    doc.text(20, 120, this.survey6.question);

    const votes = this.survey6.value1 + this.survey6.value2 + this.survey6.value3 + this.survey6.value4 + this.survey6.value5 + this.survey6.value6;

    doc.autoTable({
      startY: 140,
      head: [['Odpowiedz', 'Liczba glosow', 'Procent']],
      body: [
        [this.survey6.answer1, this.survey6.value1, (this.survey6.value1 / votes) * 100],
        [this.survey6.answer2, this.survey6.value2, (this.survey6.value2 / votes) * 100],
        [this.survey6.answer3, this.survey6.value3, (this.survey6.value3 / votes) * 100],
        [this.survey6.answer4, this.survey6.value4, (this.survey6.value4 / votes) * 100],
        [this.survey6.answer5, this.survey6.value5, (this.survey6.value5 / votes) * 100],
        [this.survey6.answer6, this.survey6.value6, (this.survey6.value6 / votes) * 100],
        // ...
      ],
    });

    doc.save('raport.pdf');
  }

  onSelect(data): void {
    console.log("Item clicked", JSON.parse(JSON.stringify(data)));
  }

  onActivate(data): void {
    console.log("Activate", JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log("Deactivate", JSON.parse(JSON.stringify(data)));
  }

  getMe() {
    this.userService.getMe().subscribe(
      user => {
      this.id = user.id;
      }
    );
  }

  deleteSurvey() {
    this.hidden = true;
    this.surveyService.deleteSurveys6(this.survey6.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  generateRaport() {
    this.surveyService.generateSurveys6Raport(this.survey6.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }
}
