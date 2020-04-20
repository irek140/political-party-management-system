import { Component, OnInit } from '@angular/core';
import { Survey3 } from 'src/app/model/surveys/Survey3';
import { SurveyService } from 'src/app/services/survey.service';
import { ToastrService } from 'ngx-toastr';
import { RegionService } from 'src/app/services/region.service';
import { Region } from 'src/app/model/Region';
import { Observable } from 'rxjs';
import { ConstituencyService } from 'src/app/services/constituency.service';
import { Constituency } from 'src/app/model/Constituency';

@Component({
  selector: 'app-survey3',
  templateUrl: './survey3.component.html',
  styleUrls: ['./survey3.component.css']
})
export class Survey3Component implements OnInit {

  question: string;
  answer1: string;
  answer2: string;
  answer3: string;
  constituency: string;
  region: string;
  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];
  allConstituencies: Observable<Constituency[]>;
  enddate: Date;

  minDate: Date;

  onlyForMembers: boolean;

  survey3: Survey3;

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
    console.log(this.survey3);
    if (!this.onlyForMembers) {
      this.surveyService.createSurvey3( this.survey3 ).subscribe(
        data => {
          console.log(data);
          this.showSuccessAlert();
          this.question = '';
          this.answer1 = '';
          this.answer2 = '';
          this.answer3 = '';
          this.region = '';
          this.constituency = '';
          this.enddate = null;
        },
        error => console.log(error));
    } else {
      this.surveyService.createSurvey3ForMembers( this.survey3 ).subscribe(
        data => {
          console.log(data);
          this.showSuccessAlert();
          this.question = '';
          this.answer1 = '';
          this.answer2 = '';
          this.answer3 = '';
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
    this.survey3 = {question: this.question, answer1: this.answer1, answer2: this.answer2, answer3: this.answer3, constituency: this.constituency, region: this.region, enddate: this.enddate};
  }
}
