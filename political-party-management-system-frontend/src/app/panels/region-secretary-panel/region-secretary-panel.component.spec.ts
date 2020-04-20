import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionSecretaryComponent } from './region-secretary-panel.component';

describe('RegionSecretaryComponent', () => {
  let component: RegionSecretaryComponent;
  let fixture: ComponentFixture<RegionSecretaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegionSecretaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegionSecretaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
