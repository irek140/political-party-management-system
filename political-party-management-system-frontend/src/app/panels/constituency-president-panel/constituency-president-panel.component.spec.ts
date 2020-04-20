import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConstituencyPresidentPanelComponent } from './constituency-president-panel.component';

describe('ConstituencyPresidentPanelComponent', () => {
  let component: ConstituencyPresidentPanelComponent;
  let fixture: ComponentFixture<ConstituencyPresidentPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConstituencyPresidentPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConstituencyPresidentPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
