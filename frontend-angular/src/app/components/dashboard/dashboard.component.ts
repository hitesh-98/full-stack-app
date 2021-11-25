import { Component, OnInit } from '@angular/core';
import { ResultService } from 'src/app/services/result.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {

  constructor(private resultService: ResultService) {}

  public productlist: any;

  ngOnInit(): void {
      this.resultService.getResults().subscribe(
        (res) => {
          console.log(res);
          this.productlist = res;
        },
        (err) => {
          console.log(err);
        }
      );
    }



}
