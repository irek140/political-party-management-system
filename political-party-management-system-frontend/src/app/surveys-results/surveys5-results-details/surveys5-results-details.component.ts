import { Component, OnInit, Input } from '@angular/core';
import { SurveysResultsListComponent } from '../surveys-results-list/surveys-results-list.component';
import { SurveyService } from 'src/app/services/survey.service';
import { Survey5 } from 'src/app/model/surveys/Survey5';
import { UserService } from 'src/app/services/user.service';
import * as jsPDF from 'jspdf';
import 'jspdf-autotable';

@Component({
  selector: 'app-surveys5-results-details',
  templateUrl: './surveys5-results-details.component.html',
  styleUrls: ['./surveys5-results-details.component.css']
})
export class Surveys5ResultsDetailsComponent implements OnInit {

  @Input()
  survey5: Survey5;

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
    console.log(this.survey5.id + ' ' + this.survey5.question);
    this.getMe();
    this.hidden = false;
    this.surveyObject = [
      {
        "name": this.survey5.answer1,
        "value": this.survey5.value1
      }, {
        "name": this.survey5.answer2,
        "value": this.survey5.value2
      }, {
        "name": this.survey5.answer3,
        "value": this.survey5.value3
      }, {
        "name": this.survey5.answer4,
        "value": this.survey5.value4
      }, {
        "name": this.survey5.answer5,
        "value": this.survey5.value5
      }
    ];
    this.numberOfVotes = this.survey5.value1 + this.survey5.value2 + this.survey5.value3 + this.survey5.value4 + this.survey5.value5;
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
    doc.text(20, 60, 'Autor: ' + this.survey5.owner.name + ' ' + this.survey5.owner.surname);
    doc.text(20, 70, 'Glosowanie od: ' + this.survey5.created);
    doc.text(20, 80, 'Glosowanie do: ' + this.survey5.enddate);
    doc.text(20, 90, 'Data utworzenia raportu: ' + this.tooday);
    doc.text(20, 110, 'Tresc pytania: ');
    doc.text(20, 120, this.survey5.question);

    const votes = this.survey5.value1 + this.survey5.value2 + this.survey5.value3 + this.survey5.value4 + this.survey5.value5;

    doc.autoTable({
      startY: 140,
      head: [['Odpowiedz', 'Liczba glosow', 'Procent']],
      body: [
        [this.survey5.answer1, this.survey5.value1, (this.survey5.value1 / votes) * 100],
        [this.survey5.answer2, this.survey5.value2, (this.survey5.value2 / votes) * 100],
        [this.survey5.answer3, this.survey5.value3, (this.survey5.value3 / votes) * 100],
        [this.survey5.answer4, this.survey5.value4, (this.survey5.value4 / votes) * 100],
        [this.survey5.answer5, this.survey5.value5, (this.survey5.value5 / votes) * 100],
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
    this.surveyService.deleteSurveys5(this.survey5.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  generateRaport() {
    this.surveyService.generateSurveys5Raport(this.survey5.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

}
