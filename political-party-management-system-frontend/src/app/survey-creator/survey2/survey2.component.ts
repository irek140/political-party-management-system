import { Component, OnInit } from '@angular/core';
import { Survey2 } from 'src/app/model/surveys/survey2';
import { SurveyService } from 'src/app/services/survey.service';
import { ToastrService } from 'ngx-toastr';
import { RegionService } from 'src/app/services/region.service';
import { Region } from 'src/app/model/Region';
import { Observable } from 'rxjs';
import { ConstituencyService } from 'src/app/services/constituency.service';
import { Constituency } from 'src/app/model/Constituency';

@Component({
  selector: 'app-survey2',
  templateUrl: './survey2.component.html',
  styleUrls: ['./survey2.component.css']
})
export class Survey2Component implements OnInit {

  question: string;
  answer1: string;
  answer2: string;
  constituency: string;
  region: string;
  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];
  allConstituencies: Observable<Constituency[]>;
  enddate: Date;

  minDate: Date;

  survey2: Survey2;

  onlyForMembers: boolean;

  constructor(private surveyService: SurveyService, private constituencyService: ConstituencyService, private regionservice: RegionService, private toastService: ToastrService) { }

  ngOnInit() {
    this.region = '';
    this.constituency = '';
    this.getRegions();
    this.getAllConstituencies();
    this.minDate = new Date();
    this.onlyForMembers = false;
  }

  getAllConstituencies() {
    this.allConstituencies = this.constituencyService.getAllConstituency();
  }

  getRegions() {
    this.regions = this.regionservice.getRegionList();
  }

  publishSurvey() {
    this.initSurvey();
    console.log(this.survey2);
    if (!this.onlyForMembers) {
      this.surveyService.createSurvey2( this.survey2 ).subscribe(
        data => {
          console.log(data);
          this.showSuccessAlert();
          this.question = '';
          this.answer1 = '';
          this.answer2 = '';
          this.region = '';
          this.constituency = '';
          this.enddate = null;
        },
        error => console.log(error));
    } else {
      this.surveyService.createSurvey2ForMembers( this.survey2 ).subscribe(
        data => {
          console.log(data);
          this.showSuccessAlert();
          this.question = '';
          this.answer1 = '';
          this.answer2 = '';
          this.region = '';
          this.constituency = '';
          this.enddate = null;
        },
        error => console.log(error));
    }

  }

  showSuccessAlert() {
    this.toastService.success('Ankieta utworzona!', 'SUKCES!');
  }

  initSurvey() {
    this.survey2 = {question: this.question, answer1: this.answer1, answer2: this.answer2, constituency: this.constituency, region: this.region, enddate: this.enddate};
  }

}
