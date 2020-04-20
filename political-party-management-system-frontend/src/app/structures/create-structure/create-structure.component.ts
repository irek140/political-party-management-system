import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Region } from 'src/app/model/Region';
import { RegionService } from 'src/app/services/region.service';

@Component({
  selector: 'app-create-structure',
  templateUrl: './create-structure.component.html',
  styleUrls: ['./create-structure.component.css']
})
export class CreateStructureComponent implements OnInit {

  region: string;
  regions: Observable<Region[]>;

  constructor(private regionService: RegionService) { }

  ngOnInit() {
    this.reloadData();
  }

  createRegion() {
    this.regionService.createRegion(this.region).subscribe(
      data => {
        console.log(data);
      },
      error => console.log(error));
    this.region = '';
    this.reloadData();
  }

  reloadData() {
    this.regions = this.regionService.getRegionList();
  }

}
